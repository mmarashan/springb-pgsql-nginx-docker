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

    /**
     * Save sensor reading to system
     * @param readings - list of new readings in json (SensorReadingApiModel)
     */
    @PostMapping("/api/save")
    void save(@RequestBody List<SensorReadingApiModel> readings) {
        sensorReadingService.save(readings);
    }

    /**
     * Request history of readings on one sensor
     * @param sensorId
     * @param from - from timestamp
     * @param to - to timestamp
     * @return list of readings on one sensor in json (SensorReadingApiModel)
     */
    @GetMapping("/api/history")
    List<SensorReadingApiModel> history(@RequestParam("id") Long sensorId,
                                        @RequestParam("from") Long from,
                                        @RequestParam("to") Long to) {
        return sensorReadingService.history(sensorId, from, to);
    }

}
