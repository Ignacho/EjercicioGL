package com.example.ejercicio.repository;

import com.example.ejercicio.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Se encarga de gestionar todas las operaciones de persistencia contra la tabla Phones.
 * 
 * @author Ignacio Barberis
 * @since 10/06/2021
 * @version 1.0
 */
@Repository
public interface UserPhoneRepository extends JpaRepository<Phone, Integer> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM PHONES WHERE user_id = :id", nativeQuery = true)
	void deleteByUserId(@Param("id") Integer id);

	List<Phone> findByUserId(@Param("userId") Integer userId);
}
