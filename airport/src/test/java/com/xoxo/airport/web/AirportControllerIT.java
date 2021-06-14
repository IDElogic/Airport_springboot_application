package com.xoxo.airport.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.xoxo.airport.dto.AirportDto;

@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class AirportControllerIT {
	
	private static final String BASE_URI="/api/airports";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testThatCreatedAirportIsListed() throws Exception {
		List<AirportDto> airportsBefore = getAllAirports();
		AirportDto newAirport = new AirportDto(5,"dfsda","IGH");
		createAirport(newAirport);
		
		List<AirportDto> airportsAfter = getAllAirports();
		
		assertThat(airportsAfter.subList(0, airportsBefore.size()))
			.usingFieldByFieldElementComparator()
			.containsExactlyElementsOf(airportsBefore);
	
		assertThat(airportsAfter.get(airportsAfter.size()-1))
			.usingRecursiveComparison()
			.isEqualTo(newAirport);
	}

	private void createAirport(AirportDto newAirport) {
		webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newAirport)
			.exchange()
			.expectStatus()
			.isOk();
	}

	private List<AirportDto> getAllAirports() {
		List<AirportDto> responseList = webTestClient
			.get()
			.uri(BASE_URI)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBodyList(AirportDto.class)
			.returnResult()
			.getResponseBody();
		Collections.sort(responseList, (a1, a2) -> Long.compare(a1.getId(), a2.getId()));
		return responseList;
	}

}
