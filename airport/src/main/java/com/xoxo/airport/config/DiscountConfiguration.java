package com.xoxo.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.xoxo.airport.service.DefaultDiscountService;
import com.xoxo.airport.service.DiscountService;

@Configuration
@Profile("!prod")
public class DiscountConfiguration {
	
	@Bean
	public DiscountService discountService() {
		return new DefaultDiscountService();
	}

}
