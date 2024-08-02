package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	
	@Query(value = "SELECT t.*\n"
			+ "FROM ticket t\n"
			+ "JOIN user u ON t.user_id = u.id\n"
			+ "WHERE u.id = :i", nativeQuery = true)
	List<Ticket> findAllTicketsByUserId(@Param("i") Integer i);
	
    @Query(value = "SELECT *\n"
    		+ "FROM ticket t\n", nativeQuery = true)
    List<Ticket> findAll();
    
    Optional<Ticket> findById(Integer id);
    
    @Query(value = "SELECT t.*\n"
			+ "FROM ticket t\n"
			+ "JOIN categoria c ON t.user_id = c.id\n"
			+ "WHERE c.id = :i", nativeQuery = true)
    List<Ticket> findAllTicketsByCategoryId (@Param("i") Integer i);
    
    @Query(value = "SELECT t.*\n"
			+ "FROM ticket t\n"
			+ "WHERE t.stato = :status", nativeQuery = true)
    List<Ticket> findAllTicketsByStatus (@Param("status") String status);
}
