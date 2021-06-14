package com.xoxo.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoxo.airport.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long>{
	
	Long countByIata(String iata);
	
	Long countByIataAndIdNot(String iata, Long id);

}
