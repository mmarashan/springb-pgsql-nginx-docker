package ru.volgadev.springbpgsqldocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volgadev.springbpgsqldocker.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
