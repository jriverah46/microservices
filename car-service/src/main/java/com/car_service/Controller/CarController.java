package com.car_service.Controller;

import com.car_service.Entity.CarEntity;
import com.car_service.Respository.CarRepository;
import com.car_service.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/all")
    public ResponseEntity<List<CarEntity>> listCars(){
        List<CarEntity>cars=carService.getAll();
        if (cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarEntity>getCar(@PathVariable("id") Long id){
        CarEntity car=carService.getCarById(id);
        if (car==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }


    //getting cars by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CarEntity>>getCarsUser(@PathVariable("userId") Long userId){
        List<CarEntity>carsUser=carService.getCarByUser(userId);
        if (carsUser.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carsUser);
    }

    @PostMapping
    public ResponseEntity<CarEntity>saveUser(@RequestBody CarEntity car){
        CarEntity newcar=carService.saveCar(car);

        return ResponseEntity.ok(newcar);
    }
}
