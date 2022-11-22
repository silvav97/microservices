package com.userservice.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userservice.models.Bike;

@FeignClient(name = "service-bike", url = "http://localhost:8083")
@RequestMapping("/bike")
public interface BikeFeignClient {
	
	@PostMapping
	public Bike save(@RequestBody Bike bike);
	
	@GetMapping("/user/{userid}")
	public List<Bike> getBikes(@PathVariable("userid") Integer userid);
	
}
