package user_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import user_service.FeignClients.CarFeignClient;
import user_service.Models.Car;
import user_service.Repository.UserRespository;
import user_service.config.RestTemplateConfig;
import user_service.entity.UserEntity;

import java.util.List;

@Service
public class UserService {
    private RestTemplate restTemplate;
    private UserRespository userRespository;
    private CarFeignClient carFeignClient;

    @Autowired
    public UserService(RestTemplate restTemplate, UserRespository userRespository, CarFeignClient carFeignClient) {
        this.restTemplate = restTemplate;
        this.userRespository = userRespository;
        this.carFeignClient = carFeignClient;
    }

    public List<UserEntity>getAll(){
        return userRespository.findAll();
    }

    public UserEntity getUserById(Long id){
        return userRespository.findById(id).orElse(null);
    }

    public UserEntity saveUser(UserEntity user){
        UserEntity newUser=userRespository.save(user);
        return newUser;
    }

    //connecting with the microservice car and getting the car list by user with restTemplate

    public List<Car>carsByUser(Long idUser){
        List<Car>cars= restTemplate
                .getForObject("http://localhost:8002/cars/user/"+idUser,List.class);
        return cars;
    }

    //saving cars from user connecting microservices with feignClient
    public Car saveCar(Long userId,Car car){
        car.setUserId(userId);
        Car newCar=carFeignClient.save(car);
        return newCar;
    }

}
