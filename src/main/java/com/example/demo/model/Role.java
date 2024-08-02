package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Role {

	@Id
	private Integer id;
	
	@NotNull
	private String name;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
}
