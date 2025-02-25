package com.car_service.Service;

import com.car_service.Entity.CarEntity;
import com.car_service.Respository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<CarEntity>getAll(){
        return carRepository.findAll();
    }

    public CarEntity getCarById(Long id){
        return carRepository.findById(id).orElse(null);
    }

    public CarEntity saveCar(CarEntity car){
        CarEntity newCar=carRepository.save(car);
        return newCar;
    }

    public List<CarEntity>getCarByUser(Long userId){

        return carRepository.findByUserId(userId);
    }
}
