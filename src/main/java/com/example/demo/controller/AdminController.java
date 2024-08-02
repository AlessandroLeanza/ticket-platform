package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.Ticket;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class AdminController {

    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String index(Model model) {
        List<Ticket> ticketList = ticketRepository.findAll();
        model.addAttribute("list", ticketList);
        return "ticket/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("users", userRepository.findAllUsersExceptAdmin());
        model.addAttribute("categorie", categoriaRepository.findAll());
        return "ticket/create";
    }

    @PostMapping("/create")
    public String createTicket(@Valid @ModelAttribute("ticket") Ticket ticketForm,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("users", userRepository.findAllUsersExceptAdmin());
            model.addAttribute("categorie", categoriaRepository.findAll());
            return "ticket/create";
        }
        ticketForm.setDataCreazione(LocalDate.now());
        ticketRepository.save(ticketForm);
        return "redirect:/tickets";
    }

    @GetMapping("/show/{id}")
    public String showTicket(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("showTicket", ticketRepository.findById(id).get());
        return "ticket/show";
    }

    @GetMapping("/edit/{id}")
    public String editTicket(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ticket", ticketRepository.findById(id).get());
        return "ticket/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTicket(@Valid @ModelAttribute("ticket") Ticket ticket,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ticket/edit";
        }
        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteTicket(@PathVariable("id") Integer id, Model model) {
        Ticket ticket = ticketRepository.findById(id).get();
        model.addAttribute("ticket", ticket);
        return "ticket/delete";
    }
  
    @PostMapping("/delete/{id}")
    public String deleteTicket(@PathVariable("id") Integer id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }
    
}