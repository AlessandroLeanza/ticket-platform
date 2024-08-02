package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.demo.model.Ticket;

public interface TicketPlatformService {

	public List<Ticket> findAllByCategoryId(Integer categoryId);
	
	public List<Ticket> findall();
	
	public List<Ticket> findAllTicketsByStatus (String status);
	
}
