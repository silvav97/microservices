package com.carservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carservice.entity.Car;
import com.carservice.repository.CarRepository;


@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	public List<Car> getAll() {
		return carRepository.findAll();
	}
	
	public Car getCarById(Integer id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Car save(Car car) {
		return carRepository.save(car);
	}
	
	public List<Car> getCarByUserId(Integer userId) {
		return carRepository.findByUserId(userId);
	}
}

