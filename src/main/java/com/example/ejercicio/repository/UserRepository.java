package com.example.ejercicio.repository;

import com.example.ejercicio.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * Se encarga de gestionar todas las operaciones de persistencia contra la tabla User.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "UPDATE USERS SET last_login = :lastlogin WHERE id = :id", nativeQuery = true)
	void updateUserLastLogin(@Param("id") Integer id, @Param("lastlogin") Timestamp lastLogin);

	List<User> findByIsActive(boolean isActive);
}
