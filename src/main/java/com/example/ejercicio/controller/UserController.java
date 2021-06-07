package com.example.ejercicio.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.ResponseDTO;
import com.example.ejercicio.dto.UserResponseDTO;
import com.example.ejercicio.exception.ExistingMailException;
import com.example.ejercicio.service.UserService;

/**
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {
	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * Obtiene todos los usuarios cargados.
	 * 
	 * @return List<User>
	 */
	@GetMapping("/all")
	public List<User> getUsers() {
		return userService.getUsers();
	}

	/**
	 * Agrega un usuario.
	 * 
	 * @param user
	 * @param token
	 * @return ResponseEntity<UserResponseDTO>
	 * @throws Exception
	 */
	@PostMapping("/add")
	public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody User user,
			@RequestHeader("Authorization") String token) throws Exception {
		log.info("Usuario {} con Bearer: {}", user.getUserName(), token.substring(7));
		user.setToken(token.substring(7));
		try {
			return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
		} catch (ExistingMailException ex) {
			throw new ExistingMailException(ex.getMessage());
		}
	}

	/**
	 * Actualiza un usuario.
	 * 
	 * @param user
	 * @return ResponseEntity<ResponseDTO>
	 */
	@PutMapping("/upd")
	public ResponseEntity<UserResponseDTO> updUser(@Valid @RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.updUser(user), HttpStatus.OK);
		} catch (EmptyResultDataAccessException ne) {
			throw new EmptyResultDataAccessException(ne.getMessage(), user.getId());
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * Elimina un usuario.
	 * 
	 * @param id
	 * @return ResponseEntity<ResponseDTO>
	 */
	@DeleteMapping("/del/{id}")
	public ResponseEntity<ResponseDTO> delUser(@PathVariable Integer id) {
		try {
			userService.delUser(id);
			return new ResponseEntity<>(new ResponseDTO("Usuario eliminado correctamente"), HttpStatus.OK);
		} catch (EmptyResultDataAccessException ne) {
			throw new EmptyResultDataAccessException(ne.getMessage(), id);
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

}
