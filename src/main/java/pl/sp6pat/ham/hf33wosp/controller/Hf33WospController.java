package pl.sp6pat.ham.hf33wosp.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sp6pat.ham.hf33wosp.repositories.cl.LastHeard;
import pl.sp6pat.ham.hf33wosp.repositories.cl.OperatorPoints;
import pl.sp6pat.ham.hf33wosp.service.Hf33WospService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Hf33WospController {

    private final Hf33WospService hf33WospService;

    public Hf33WospController(Hf33WospService hf33WospService) {
        this.hf33WospService = hf33WospService;
    }

    @GetMapping("/last-heard")
    public List<LastHeard> lastHeard(@PathParam("stationId") Integer stationId, @PathParam("diffInSec") Integer diffInSec, @PathParam("limit") Integer limit) {
        //TODO: walidacja parametrów
//        if (stationId == null || diffInSec == null || limit == null) {
//            return "Incorrect params";
//        }
        return hf33WospService.getLastHeard(stationId, diffInSec, limit);
    }

    @GetMapping("/points")
    public OperatorPoints points(@PathParam("stationId") Integer stationId, @PathParam("call") String call) {
        //TODO: nieprawidłowe parametry
//        if (stationId == null) {
//            return "Incorrect params";
//        }
        System.out.println("Station id: " + stationId);
        System.out.println("Call: " + call);
        return hf33WospService.getPoints(stationId, call);
    }

    @GetMapping("/hf33wosp-get-image")
    public ResponseEntity<Resource> getImage(@PathParam("call") String call) {
        System.out.println("Call: " + call);
        try {
            Resource image = hf33WospService.getImage(call);
            if (!image.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Jeśli plik nie istnieje
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "image/png");
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Obsługa błędów
        }
    }

}
