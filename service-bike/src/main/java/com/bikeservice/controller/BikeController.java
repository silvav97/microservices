package com.bikeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeservice.entity.Bike;
import com.bikeservice.service.BikeService;



@RestController
@RequestMapping("/bike")
public class BikeController {

	@Autowired
	private BikeService bikeService;
	
	@GetMapping
	public ResponseEntity<List<Bike>> listBikes() {
		List<Bike> bikes = bikeService.getAll();
		if(bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bikes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bike> getBike(@PathVariable("id") Integer id) {
		Bike bike = bikeService.getBikeById(id);
		if(bike == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bike);
	}
	
	@PostMapping
	public ResponseEntity<Bike> saveBike(@RequestBody Bike bike) {
		Bike newBike = bikeService.save(bike);
		return ResponseEntity.ok(newBike);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<List<Bike>> listBikeByUserId(@PathVariable("userid") Integer userid) {
		List<Bike> bikes = bikeService.getBikeByUserId(userid);
		if(bikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bikes);
	}
	
}