package com.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userservice.models.Car;

@FeignClient(name = "service-car") //without gateway-> url = "http://localhost:8082"
@RequestMapping("/car")
public interface CarFeignClient {
	
	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("/user/{userid}")
	public List<Car> getCars(@PathVariable("userid") Integer userid);
	

}
