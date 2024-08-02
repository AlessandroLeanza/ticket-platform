package com.example.demo.controller;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.model.Note;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TicketService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/operatori")
public class OperatoreController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;
    

    @GetMapping("/{operatoreId}/tickets")
    public String listaTicketOperatore(@PathVariable("operatoreId") Integer operatoreId, Model model) {

        List<Ticket> ticketList = ticketRepository.findAllTicketsByUserId(operatoreId);
        model.addAttribute("tickets", ticketList);     
        return "/operatori/indexUser";
    }

    
    @GetMapping("/{operatoreId}/tickets/{ticketId}")
    public String dettaglioTicket(@PathVariable("operatoreId") Integer operatoreId, 
    							  @PathVariable("ticketId") Integer ticketId, Model model) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        model.addAttribute("ticket", ticket);
        return "/operatori/showUser";
    }

    
    @GetMapping("/{operatoreId}/tickets/{ticketId}/aggiorna-stato")
    public String visualizzaStatoTicket(@PathVariable("operatoreId") Integer operatoreId, 
                                        @PathVariable("ticketId") Integer ticketId, Model model) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        model.addAttribute("ticket", ticket);
        model.addAttribute("operatoreId", operatoreId);
        return "/operatori/updateTicketStatus";
    }

    @PostMapping("/{operatoreId}/tickets/{ticketId}/aggiorna-stato")
    public String aggiornaStatoTicket(@PathVariable("operatoreId") Integer operatoreId,
                                      @PathVariable("ticketId") Integer ticketId,
                                      @Valid @ModelAttribute("ticket") Ticket ticket,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/operatori/updateTicketStatus";
        }
        
        String stato = ticket.getStato();
        model.addAttribute("stato", stato);
        System.out.println("Questo è il mio stato: " + stato);
        ticket.setStato(stato);
        ticketRepository.save(ticket);
        return "redirect:/operatori/" + operatoreId + "/tickets";
    }
     
    @PostMapping("/{operatoreId}/tickets/{ticketId}/aggiungi-nota")
    public String aggiungiNotaTicket(@PathVariable("operatoreId") Integer operatoreId, 
    								 @PathVariable("ticketId") Integer ticketId, 
    								 @RequestParam("nota") String nota) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        if (ticket != null) {
        	Note note = new Note();
            note.setTesto(nota);
            note.setDataCreazione(LocalDate.now());
            ticket.aggiungiNota(note);
            ticketRepository.save(ticket);
        }
        return "redirect:/operatori/{operatoreId}/tickets/{/ticketId}";
    }

    @GetMapping("/{id}/modifica")
    public String modificaDatiOperatore(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        return "operatore/modifica";
    }

    @PostMapping("/{id}/modifica")
    public String aggiornaOperatore(@PathVariable("id") Integer id, 
    								@Valid @ModelAttribute User user,
    								BindingResult bindingresult, Model model) {
    	
        if (bindingresult.hasErrors()) {
            model.addAttribute("user", user);
            return "operatore/modifica";
        }
        userRepository.save(user);
        return "redirect:/operatori/{id}/tickets";
    }
}