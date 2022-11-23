package com.bikeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceBikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBikeApplication.class, args);
	}

}
