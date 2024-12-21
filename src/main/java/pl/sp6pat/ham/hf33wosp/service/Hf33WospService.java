package pl.sp6pat.ham.hf33wosp.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.sp6pat.ham.hf33wosp.repositories.*;

import java.util.List;

@Service
public class Hf33WospService {

    private final CloudlogHrdContactsLeaderRepository leaderRepository;
    private final CloudlogLastHrdRepository lastHrdRepository;

    public Hf33WospService(CloudlogHrdContactsLeaderRepository leaderRepository, CloudlogLastHrdRepository lastHrdRepository) {
        this.leaderRepository = leaderRepository;
        this.lastHrdRepository = lastHrdRepository;
    }

    public List<LastHeard> getLastHeard(Integer stationId, Integer diffInSec, Integer limit) {
        return lastHrdRepository.findLastHeardNative(stationId, diffInSec, limit);
    }

    public Leader getPoints(Integer stationId, String call) {
        Leader leader = leaderRepository.calculatePointsByCallNative(stationId, call);
        System.out.println("Points for call " + call + ": " + (leader != null? leader.getPoints(): "nie znaleziono QSO"));
        return leader;

    }

    public Resource getImage(String call) {
        Resource image = new ClassPathResource("static/sn32wosp_template_cert.png");
        //TODO: transform
        return image;
    }
}
