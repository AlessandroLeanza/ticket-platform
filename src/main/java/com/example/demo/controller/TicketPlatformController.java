package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ticket;
import com.example.demo.response.Payload;
import com.example.demo.service.TicketPlatformService;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket")
public class TicketPlatformController {

	@Autowired
	private TicketPlatformService ticketPlatformService;
	
	@GetMapping("/allTickets")
	public ResponseEntity<Payload<List<Ticket>>> getAll(){
		
		List<Ticket> ticketList = ticketPlatformService.findall();
		
		return ResponseEntity.ok(new Payload<List<Ticket>>(ticketList, null, HttpStatus.OK));
	}
	
	 @GetMapping("/{categoryId}")
	 public ResponseEntity<Payload<List<Ticket>>> getId(@PathVariable("categoryId") Integer categoryId){
	 
		 List<Ticket> ticketList = ticketPlatformService.findAllByCategoryId(categoryId);
	   
		 return ResponseEntity.ok(new Payload<List<Ticket>>(ticketList, null, HttpStatus.OK));   
	     
	 }
	 
	 @GetMapping("/status/{status}")
	    public ResponseEntity<Payload<List<Ticket>>> getAllByStatus(@PathVariable("status") String status) {
		 
	        List<Ticket> ticketList = ticketPlatformService.findAllTicketsByStatus(status);
	        	        
	        return ResponseEntity.ok(new Payload<List<Ticket>>(ticketList, null, HttpStatus.OK)); 
	    }
}
