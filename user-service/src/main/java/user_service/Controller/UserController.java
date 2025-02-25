package user_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.Models.Car;
import user_service.Service.UserService;
import user_service.entity.UserEntity;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/all")
    public ResponseEntity<List<UserEntity>>listUsers(){
        List<UserEntity>users=userService.getAll();
        if (users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity>getUser(@PathVariable("id") Long id){
        UserEntity user=userService.getUserById(id);
        if (user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save-user")
    public ResponseEntity<UserEntity>saveUser(@RequestBody UserEntity user){
        UserEntity newuser=userService.saveUser(user);

        return ResponseEntity.ok(newuser);
    }

    @GetMapping("cars/{userId}")
    public ResponseEntity<List<Car>>getCarsByUser(@PathVariable("userId" ) Long userId){
        UserEntity user=userService.getUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }

        List<Car>cars=userService.carsByUser(userId);

        if (cars.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    //method to save cars with feignClient
    @PostMapping("/save-car/{userId}")
    public ResponseEntity<Car>saveCar(@PathVariable("userId") Long userId,@RequestBody Car car){
        Car newCar=userService.saveCar(userId,car);
        return ResponseEntity.ok(newCar);
    }
}
