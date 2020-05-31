package ru.volgadev.springbpgsqldocker.repository;

import ru.volgadev.springbpgsqldocker.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<UserModel, Long> {
}
