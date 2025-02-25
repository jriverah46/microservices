package user_service.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import user_service.Models.Car;

@FeignClient(name = "car-service",url = "http://localhost:8002")
public interface CarFeignClient {
    @PostMapping("/cars")
    public Car save(@RequestBody Car car);
}
