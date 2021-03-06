package com.xoxo.airport.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Airport {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min = 3, max = 20)
	private String name;
	private String iata;
	
	public Airport() {
		
	}
	
	public Airport(long id, String name, String iata) {
		super();
		this.id = id;
		this.name = name;
		this.iata = iata;
	}
	public Airport(String name, String iata) {
		super();
		this.name = name;
		this.iata = iata;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	
	

}
