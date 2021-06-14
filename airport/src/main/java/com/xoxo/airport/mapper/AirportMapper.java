package com.xoxo.airport.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.xoxo.airport.dto.AirportDto;
import com.xoxo.airport.model.Airport;

@Mapper(componentModel = "spring")
public interface AirportMapper {

	List<AirportDto> airportsToDtos(List<Airport> airports);

	AirportDto airportToDto(Airport airport);

	Airport dtoToAirport(AirportDto airportDto);
	
}
