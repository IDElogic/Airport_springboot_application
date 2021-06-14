package com.xoxo.airport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoxo.airport.model.LogEntry;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long>{

}
