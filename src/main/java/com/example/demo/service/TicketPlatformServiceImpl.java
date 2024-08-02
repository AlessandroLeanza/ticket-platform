package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;


@Service
public class TicketPlatformServiceImpl implements TicketPlatformService {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> findAllByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return ticketRepository.findAllTicketsByCategoryId(categoryId);
	}

	@Override
	public List<Ticket> findall() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	@Override
	public List<Ticket> findAllTicketsByStatus(String status) {
		// TODO Auto-generated method stub
		return ticketRepository.findAllTicketsByStatus(status);
	}
	
	
}
