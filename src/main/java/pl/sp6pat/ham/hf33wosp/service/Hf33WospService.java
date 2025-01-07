package pl.sp6pat.ham.hf33wosp.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.sp6pat.ham.hf33wosp.repositories.cl.OperatorPointsRepository;
import pl.sp6pat.ham.hf33wosp.repositories.cl.CloudlogLastHrdRepository;
import pl.sp6pat.ham.hf33wosp.repositories.cl.LastHeard;
import pl.sp6pat.ham.hf33wosp.repositories.cl.OperatorPoints;
import pl.sp6pat.ham.hf33wosp.repositories.sc.Schedule;
import pl.sp6pat.ham.hf33wosp.repositories.sc.ScheduleRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class Hf33WospService {

    private final OperatorPointsRepository leaderRepository;
    private final CloudlogLastHrdRepository lastHrdRepository;
    private final ScheduleRepository scheduleRepository;

    public Hf33WospService(OperatorPointsRepository leaderRepository, CloudlogLastHrdRepository lastHrdRepository, ScheduleRepository scheduleRepository) {
        this.leaderRepository = leaderRepository;
        this.lastHrdRepository = lastHrdRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<LastHeard> getLastHeard(Integer stationId, Integer diffInSec, Integer limit) {
        return lastHrdRepository.findLastHeardNative(stationId, diffInSec, limit);
    }

    public OperatorPoints getPoints(Integer stationId, String call) {
        OperatorPoints operatorPoints = leaderRepository.calculatePointsByCallNative(stationId, call);
        System.out.println("Points for call " + call + ": " + (operatorPoints != null? operatorPoints.getPoints(): "nie znaleziono QSO"));
        return operatorPoints;

    }

    public Resource getImage(String call) throws IOException {
        Resource imageResource = new ClassPathResource("static/hf33wosp_template_cert.png");
        InputStream in = imageResource.getInputStream();
        BufferedImage image = ImageIO.read(in);

        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//        Font f = new Font("Arial", Font.BOLD, 90);
        Font f = loadFont();
        g.setFont(f);


        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(call.toUpperCase());
        int textHeight = fm.getHeight();
        int x = (image.getWidth() - textWidth) / 2;
        int y = ((image.getHeight() - textHeight) / 2 + fm.getAscent()) - 50; // fm.getAscent() jest potrzebne, aby pionowo wyśrodkować tekst w obliczonym obszarze.

        Color shadowColor = new Color(100, 100, 100, 150);
        int shadowOffset = 4;

        g.setColor(shadowColor);
        g.drawString(call.toUpperCase(), x + shadowOffset, y + shadowOffset);

        g.setColor(Color.WHITE);
        g.drawString(call.toUpperCase(), x, y);
        g.dispose();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);

        return new ByteArrayResource(os.toByteArray());
    }

    public List<Schedule> getSchedule() {
//        return scheduleRepository.findByDateGreaterThanEqual(LocalDate.now());
        return scheduleRepository.findByOrderByDateAsc();
    }

    private Font loadFont() {
        Font customFont;
        try {
            InputStream fontStream = getClass().getResourceAsStream("/static/RobotoCondensed-VariableFont_wght.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Nie znaleziono pliku czcionki w zasobach.");
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(Font.BOLD, 90f);
            fontStream.close();
            return customFont;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
