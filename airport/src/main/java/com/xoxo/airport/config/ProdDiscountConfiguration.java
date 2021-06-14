package com.xoxo.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.xoxo.airport.service.DiscountService;
import com.xoxo.airport.service.SpecialDiscountService;

@Configuration
@Profile("prod")
public class ProdDiscountConfiguration {
	
	@Bean
	public DiscountService discountService() {
		return new SpecialDiscountService();
	}

}
