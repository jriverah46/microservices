package com.car_service.Respository;

import com.car_service.Entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {
    List<CarEntity>findByUserId(Long userId);
}
