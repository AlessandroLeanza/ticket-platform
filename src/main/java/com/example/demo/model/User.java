package com.example.demo.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

@Entity
public class User {

	
	@ManyToMany (fetch = FetchType.EAGER)
	private Set<Role> roles;

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @NotBlank (message = "Devi inserire il nome")
		@Column (name = "username", nullable = false)
	    private String username;
	    
	    @NotBlank (message = "Devi inserire la password")
		@Column (name = "password", nullable = false)
	    private String password;
	    
	    @NotNull (message = "Devi dire se sei non disponibile")
	    @Column(name = "nonDisponibile", nullable = false)
	    private boolean nonDisponibile;
	    
	    @NotBlank (message = "Devi inserire il task assegnato")
		@Column (name = "taskAssegnato", nullable = false)
	    private String taskAssegnato;
	    
	    @OneToMany(mappedBy = "user")
	    @JsonManagedReference
	    private List<Ticket> ticketList;

	public Set<Role> getRoles() {
		return roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isNonDisponibile() {
		return nonDisponibile;
	}

	public void setNonDisponibile(boolean nonDisponibile) {
		this.nonDisponibile = nonDisponibile;
	}

	public String getTaskAssegnato() {
		return taskAssegnato;
	}

	public void setTaskAssegnato(String taskAssegnato) {
		this.taskAssegnato = taskAssegnato;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(List<Ticket> ticketList) {
		this.ticketList = ticketList;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
