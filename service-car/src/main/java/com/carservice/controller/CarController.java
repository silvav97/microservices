package com.carservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carservice.entity.Car;
import com.carservice.service.CarService;


@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;
	
	@GetMapping
	public ResponseEntity<List<Car>> listCars() {
		List<Car> cars = carService.getAll();
		if(cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCar(@PathVariable("id") Integer id) {
		Car car = carService.getCarById(id);
		if(car == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@PostMapping
	public ResponseEntity<Car> saveCar(@RequestBody Car car) {
		Car newCar = carService.save(car);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<Car>> listCarByUserId(@PathVariable("userid") Integer userid) {
		List<Car> cars = carService.getCarByUserId(userid);
		if(cars.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cars);
	}
	
}
