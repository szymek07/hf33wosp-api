package pl.sp6pat.ham.hf33wosp.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pl.sp6pat.ham.hf33wosp.repositories.cl.OperatorPointsRepository;
import pl.sp6pat.ham.hf33wosp.repositories.cl.CloudlogLastHrdRepository;
import pl.sp6pat.ham.hf33wosp.repositories.cl.LastHeard;
import pl.sp6pat.ham.hf33wosp.repositories.cl.OperatorPoints;
import pl.sp6pat.ham.hf33wosp.repositories.sc.ScheduleRepository;

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

    public Resource getImage(String call) {
        Resource image = new ClassPathResource("static/sn32wosp_template_cert.png");
        //TODO: transform
        return image;
    }
}
