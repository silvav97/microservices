package com.userservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.entity.User;
import com.userservice.feignclients.BikeFeignClient;
import com.userservice.feignclients.CarFeignClient;
import com.userservice.models.Bike;
import com.userservice.models.Car;
import com.userservice.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CarFeignClient carFeignClient;
	
	@Autowired
	private BikeFeignClient bikeFeignClient;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	
	
	// REST TEMPLATE
	public List<Car> getCarsByUserId(Integer userId) {
		List<Car> cars = restTemplate.getForObject("http://service-car/car/user/" + userId, List.class);
		return cars;
	}
	// REST TEMPLATE
	public List<Bike> getBikesByUserId(Integer userId) {
		List<Bike> bikes = restTemplate.getForObject("http://service-bike/bike/user/" + userId, List.class);
		return bikes;
	}
	
	
	// FEIGN CLIENT
	public Car saveCar(Integer userid, Car car) {
		car.setUserId(userid);
		Car newCar = carFeignClient.save(car);
		return newCar;
	}
	
	// FEIGN CLIENT
	public Bike saveBike(Integer userId, Bike bike) {
		bike.setUserId(userId);
		Bike newBike = bikeFeignClient.save(bike);
		return newBike;
	}
	
	public Map<String, Object> getUserAndVehicles(Integer userId) {
		Map<String,Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			result.put("Message", "User does not exist");
		}
		result.put("User", user);
		List<Car> cars = carFeignClient.getCars(userId);
		if(cars.isEmpty()) {
			result.put("Message", "User does not have cars");
		}else {
			result.put("Cars", cars);
		}
		List<Bike> bikes = bikeFeignClient.getBikes(userId);
		if(bikes.isEmpty()) {
			result.put("Message", "User does not have bikes");
		}else {
			result.put("Bikes", bikes);
		}
		return result;
	}
	
}
