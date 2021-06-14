package com.xoxo.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoxo.airport.model.AirportUser;

public interface UserRepository extends JpaRepository<AirportUser, String>{

}
