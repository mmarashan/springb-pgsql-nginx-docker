package ru.volgadev.springbpgsqldocker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.volgadev.springbpgsqldocker.api.apimodel.SensorReadingApiModel;
import ru.volgadev.springbpgsqldocker.service.SensorReadingService;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value="Sensors readings", description="Sensors reading API")
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
    @ApiOperation(value = "Save new reading")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully save reading"),
    }
    )
    @PostMapping("/save")
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
    @ApiOperation(value = "Get sensor history between timeout", response = List.class)
    @GetMapping("/history")
    List<SensorReadingApiModel> history(@RequestParam("id") Long sensorId,
                                        @RequestParam("from") Long from,
                                        @RequestParam("to") Long to) {
        return sensorReadingService.history(sensorId, from, to);
    }

}
