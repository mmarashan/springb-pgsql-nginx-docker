package ru.volgadev.springbpgsqldocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.volgadev.springbpgsqldocker.api.apimodel.SensorReadingApiModel;
import ru.volgadev.springbpgsqldocker.model.Sensor;
import ru.volgadev.springbpgsqldocker.model.SensorReading;

import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {

    @Query(value = "SELECT sr FROM SensorReading sr WHERE sensor_id=?1 AND time BETWEEN ?2 AND ?3")
    List<SensorReading> history(Long sensorId, Long from, Long to);
}
