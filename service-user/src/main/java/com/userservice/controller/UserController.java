package com.userservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.entity.User;
import com.userservice.models.Bike;
import com.userservice.models.Car;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> listUsers() {
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
		User user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}

	
	
	// REST TEMPLATE
	@GetMapping("/car/{userid}")
	public ResponseEntity<List<Car>> getCarsByUser(@PathVariable("userid") Integer userid) {
		User user = userService.getUserById(userid);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		List<Car> cars = userService.getCarsByUserId(userid);
		return ResponseEntity.ok(cars);
	}

	// REST TEMPLATE
	@GetMapping("/bike/{userid}")
	public ResponseEntity<List<Bike>> getBikesByUser(@PathVariable("userid") Integer userid) {
		User user = userService.getUserById(userid);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		List<Bike> bikes = userService.getBikesByUserId(userid);
		return ResponseEntity.ok(bikes);
	}

	
	// FEIGN CLIENT
	@PostMapping("/car/{userid}")
	public ResponseEntity<Car> saveCarToUser(@PathVariable("userid") Integer userid, @RequestBody Car car) {
		Car newCar = userService.saveCar(userid, car);
		return ResponseEntity.ok(newCar);
	}
	
	// FEIGN CLIENT
	@PostMapping("/bike/{userid}")
	public ResponseEntity<Bike> saveBikeToUser(@PathVariable("userid") Integer userid, @RequestBody Bike bike) {
		Bike newBike = userService.saveBike(userid, bike);
		return ResponseEntity.ok(newBike);
	}
	
	// FEIGN CLIENT
	@GetMapping("/all/{userid}")
	public ResponseEntity<Map<String,Object>> ListAllVehiclesFromUser(@PathVariable("userid") Integer userid) {
		Map<String,Object> result = userService.getUserAndVehicles(userid);
		return ResponseEntity.ok(result);
	}

}
