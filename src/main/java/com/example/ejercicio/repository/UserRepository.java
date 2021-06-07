package com.example.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ejercicio.domain.User;

/**
 * Se encarga de gestionar todas las operaciones de persistencia contra la tabla User.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserName(String username);
	
	User findByEmail(String email);
}
