package pl.sp6pat.ham.hf33wosp.service;

import org.springframework.stereotype.Service;
import pl.sp6pat.ham.hf33wosp.repositories.*;

import java.util.List;

@Service
public class CloudlogService {

    private final CloudlogHrdContactsLeaderRepository leaderRepository;
    private final CloudlogLastHrdRepository lastHrdRepository;

    public CloudlogService(CloudlogHrdContactsLeaderRepository leaderRepository,  CloudlogLastHrdRepository lastHrdRepository) {
        this.leaderRepository = leaderRepository;
        this.lastHrdRepository = lastHrdRepository;
    }

    public List<LastHeard> getLastHeard(Integer stationId, Integer diffInSec, Integer limit) {
        return lastHrdRepository.findLastHeardNative(stationId, diffInSec, limit);
    }

    public Leader getPoints(Integer stationId, String call) {
        Leader leader = leaderRepository.findAwardedByCallNative(stationId, call);
        System.out.println("Points for call " + call + " " + (leader != null? leader.getPoints(): "nie znaleziono QSO"));
        return leader;

    }
}
