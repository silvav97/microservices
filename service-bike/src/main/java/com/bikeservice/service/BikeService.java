package com.bikeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikeservice.entity.Bike;
import com.bikeservice.repository.BikeRepository;

@Service
public class BikeService {

	@Autowired
	private BikeRepository bikeRepository;

	public List<Bike> getAll() {
		return bikeRepository.findAll();
	}

	public Bike getBikeById(Integer id) {
		return bikeRepository.findById(id).orElse(null);
	}

	public Bike save(Bike bike) {
		return bikeRepository.save(bike);
	}

	public List<Bike> getBikeByUserId(Integer userId) {
		return bikeRepository.findByUserId(userId);
	}
}
