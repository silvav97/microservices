package com.bikeservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeservice.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

	List<Bike> findByUserId(Integer userId);
	
}
