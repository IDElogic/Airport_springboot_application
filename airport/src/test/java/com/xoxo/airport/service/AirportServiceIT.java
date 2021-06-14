package com.xoxo.airport.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.assertj.core.data.TemporalUnitWithinOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.xoxo.airport.model.Airport;
import com.xoxo.airport.model.Flight;
import com.xoxo.airport.repository.AirportRepository;
import com.xoxo.airport.repository.FlightRepository;

@SpringBootTest
@AutoConfigureTestDatabase
public class AirportServiceIT {

	@Autowired
	AirportService airportService;
	
	@Autowired 
	AirportRepository airportRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@BeforeEach
	public void init() {
		flightRepository.deleteAll();
		airportRepository.deleteAll();
	}
	
	@Test
	void testCreateFlight() throws Exception {
		String flightNumber = "ABC123";
		long takeoff = createAirport("airport1", "iata1");
		long landing = createAirport("airport2", "iata2");
		LocalDateTime dateTime= LocalDateTime.of(2021, 4, 23, 8, 0, 0);
		Flight flight = airportService.createFlight(flightNumber, takeoff, landing, dateTime);
		
		Optional<Flight> savedFlightOptional = flightRepository.findById(flight.getId());
		assertThat(savedFlightOptional).isNotEmpty();
		Flight savedFlight = savedFlightOptional.get();
		assertThat(savedFlight.getFlightNumber()).isEqualTo(flightNumber);
		assertThat(savedFlight.getTakeoffTime()).isCloseTo(dateTime, new TemporalUnitWithinOffset(1, ChronoUnit.MICROS));
		assertThat(savedFlight.getTakeoff().getId()).isEqualTo(takeoff);
		assertThat(savedFlight.getLanding().getId()).isEqualTo(landing);	
	}

	@Test
	void testFindFlightsByExample() throws Exception {
		long airport1Id = createAirport("airport1", "iata1");
		long airport2Id = createAirport("airport2", "iata2");
		long airport3Id = createAirport("airport3", "3iata");
		
		LocalDateTime takeoffTime = LocalDateTime.of(2022, 12, 0, 0, 0);
		long flight1Id = createFlight("ABC123", airport1Id, airport3Id, takeoffTime);	
		long flight2Id = createFlight("ABC234", airport2Id, airport3Id, takeoffTime.plusHours(2));	
				
		Flight example = new Flight();
		example.setFlightNumber("ABC123");
		example.setTakeoffTime(takeoffTime);
		example.setTakeoff(new Airport("sasa", "iata"));
		List<Flight> foundFlights = airportService.findFlightsByExapmle(example);
		assertThat(foundFlights.stream().map(Flight::getId).collect(Collectors.toList())).containsExactly(flight1Id, flight2Id);
				
	}

	private long createFlight(String flightNumber, long takeoff, long landing, LocalDateTime dateTime) {
		return airportService.createFlight(flightNumber, takeoff, landing, dateTime).getId();
	}

	private long createAirport(String name, String iata) {
		return airportRepository.save(new Airport(name,iata)).getId();
	}
	
}
