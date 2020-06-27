package ru.volgadev.springbpgsqldocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volgadev.springbpgsqldocker.model.IndustrialObject;

@Repository
public interface IndustrialObjectRepository extends JpaRepository<IndustrialObject, Long> {
}
