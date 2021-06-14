package com.xoxo.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xoxo.airport.service.AirportService;
import com.xoxo.airport.service.InitDbService;

@SpringBootApplication
public class AirportApplication implements CommandLineRunner {

	//@Autowired
	//PriceService priceService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	InitDbService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initDbService.createUsersIfNeeded();
		//airportService.createFlight();
		//System.out.println(priceService.getFinalPrice(200));
		//System.out.println(priceService.getFinalPrice(20_000));
	
		
	}
	
	

}
