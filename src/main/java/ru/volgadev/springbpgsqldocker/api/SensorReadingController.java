package ru.volgadev.springbpgsqldocker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.volgadev.springbpgsqldocker.api.apimodel.SensorReadingApiModel;
import ru.volgadev.springbpgsqldocker.service.SensorReadingService;

import java.util.List;

@RestController
public class SensorReadingController {

    private SensorReadingService sensorReadingService;

    @Autowired
    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @PostMapping("/api/save")
    void save(@RequestBody List<SensorReadingApiModel> readings) {
        sensorReadingService.save(readings);
    }


    @GetMapping("/api/history")
    List<SensorReadingApiModel> history(@RequestParam("id") Long sensorId,
                                        @RequestParam("from") Long from,
                                        @RequestParam("to") Long to) {
        return sensorReadingService.history(sensorId, from, to);
    }

}
