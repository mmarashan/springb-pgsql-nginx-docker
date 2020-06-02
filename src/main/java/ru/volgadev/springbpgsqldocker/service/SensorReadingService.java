package ru.volgadev.springbpgsqldocker.service;

import org.springframework.stereotype.Service;
import ru.volgadev.springbpgsqldocker.api.apimodel.SensorReadingApiModel;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface SensorReadingService {
    public abstract void save(List<SensorReadingApiModel> readings);
    public abstract List<SensorReadingApiModel> history(@NotNull Long sensorId, @NotNull Long from, @NotNull Long to);
    public abstract List<SensorReadingApiModel> latest(@NotNull Long objectId);
    // TODO
    public abstract void avg();
}
