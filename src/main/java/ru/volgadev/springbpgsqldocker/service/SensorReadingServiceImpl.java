package ru.volgadev.springbpgsqldocker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volgadev.springbpgsqldocker.api.apimodel.SensorReadingApiModel;
import ru.volgadev.springbpgsqldocker.model.IndustrialObject;
import ru.volgadev.springbpgsqldocker.model.Sensor;
import ru.volgadev.springbpgsqldocker.model.SensorReading;
import ru.volgadev.springbpgsqldocker.repository.IndustrialObjectRepository;
import ru.volgadev.springbpgsqldocker.repository.SensorReadingRepository;
import ru.volgadev.springbpgsqldocker.repository.SensorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SensorReadingServiceImpl implements SensorReadingService {

    private IndustrialObjectRepository industrialObjectRepository;
    private SensorReadingRepository sensorReadingRepository;
    private SensorRepository sensorRepository;

    private Logger logger = Logger.getLogger("SensorReadingServiceImpl");

    @Autowired
    public SensorReadingServiceImpl(IndustrialObjectRepository industrialObjectRepository,
                                    SensorRepository sensorRepository,
                                    SensorReadingRepository sensorReadingRepository){
        this.industrialObjectRepository = industrialObjectRepository;
        this.sensorReadingRepository = sensorReadingRepository;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public void save(List<SensorReadingApiModel> readings) {

        for (SensorReadingApiModel reading : readings){
            logger.log(Level.INFO, "Save new reading ".concat(reading.toString()));

            IndustrialObject obj;
            Sensor sensor;

            if (!industrialObjectRepository.existsById(reading.getObjectId())){
                logger.log(Level.INFO, "Create industrial object ".concat(reading.getObjectId().toString()));
                industrialObjectRepository.save(new IndustrialObject(reading.getObjectId()));
            }
            obj = industrialObjectRepository.getOne(reading.getObjectId());


            if (!sensorRepository.existsById(reading.getSensorId())){
                logger.log(Level.INFO, "Create sensor ".concat(reading.getSensorId().toString()));
                sensorRepository.save(new Sensor(reading.getSensorId(), obj));
            }
            sensor = sensorRepository.getOne(reading.getSensorId());

            SensorReading sensorReading = new SensorReading();
            sensorReading.setObject(obj);
            sensorReading.setSensor(sensor);
            sensorReading.setTime(reading.getTime());
            sensorReading.setValue(reading.getValue());

            sensorReadingRepository.save(sensorReading);
            logger.log(Level.INFO, "Saved ".concat(sensorReading.getId().toString()));
        }
    }

    // TODO: обработка случая когда нет параметров from или to (не обговорено в задаче)
    @Override
    public List<SensorReadingApiModel> history(Long sensorId, Long from, Long to) {
        logger.log(Level.INFO, "Get history for ".concat(sensorId.toString()));
        if (!sensorRepository.existsById(sensorId)){
            logger.log(Level.WARNING, "Sensor not exists".concat(sensorId.toString()));
            throw new IllegalArgumentException("Sensor not exists".concat(sensorId.toString()));
        }

        List<SensorReading> readings = sensorReadingRepository.history(sensorId, from, to);
        ArrayList<SensorReadingApiModel> result = new ArrayList();
        for (SensorReading reading : readings){
            result.add(SensorReadingApiModel.fromSensorReading(reading));
        }
        logger.log(Level.INFO, "History for ".concat(sensorId.toString())
                .concat(" return ").concat(String.valueOf(result.size()).concat(" readings")));
        return result;
    }

    @Override
    public List<SensorReadingApiModel> latest(Long objectId) {
        return null;
    }

    @Override
    public void avg() {

    }
}
