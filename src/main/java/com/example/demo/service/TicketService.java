package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

@Service
public class TicketService {
	
	@Autowired
    private TicketRepository ticketRepository;

    public Ticket updateTicketStatus(Integer ticketId, String newStatus) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setStato(newStatus);
        
        return ticketRepository.save(ticket);
    }

}
