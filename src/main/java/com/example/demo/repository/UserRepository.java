package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("SELECT o FROM Operatore o WHERE o.nonDisponibile = false AND NOT EXISTS "
//			+ "(SELECT t FROM Ticket t WHERE t.operatore = o AND t.stato IN ('Da fare', 'In corso'))")
	Optional<User> findByUsername (String username);
	
	@Query(value = "SELECT u.* "
			+ "FROM user u "
			+ "JOIN user_roles ur ON u.id = ur.user_id "
			+ "JOIN role r ON ur.roles_id = r.id "
			+ "WHERE r.name != 'Admin'", nativeQuery=true)
	List<User> findAllUsersExceptAdmin ();
}
