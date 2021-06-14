package com.xoxo.airport.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.xoxo.airport.model.LogEntry;
import com.xoxo.airport.repository.LogEntryRepository;

@Service
public class LogEntryService {

	@Autowired
	LogEntryRepository logEntryRepository;
	
	public void createLog(String description) {
		//callBackendSystem();
		logEntryRepository.save(new LogEntry(description, SecurityContextHolder
				.getContext().getAuthentication().getName()));
	}

	private void callBackendSytem() {
		if(new Random().nextInt(4) == 1)
			throw new RuntimeException();
	}
}
