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
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 250));

        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(call.toUpperCase());
        int textHeight = fm.getHeight();
        int x = (image.getWidth() - textWidth) / 2;
        int y = (image.getHeight() - textHeight) / 2 + fm.getAscent(); // fm.getAscent() jest potrzebne, aby pionowo wyśrodkować tekst w obliczonym obszarze.

        g.drawString(call.toUpperCase(), x, y);
        g.dispose();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);

        return new ByteArrayResource(os.toByteArray());
    }

    public List<Schedule> getSchedule() {
        return scheduleRepository.findByDateGreaterThanEqual(LocalDate.now());
    }
}
