package com.example.ejercicio.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.UserResponseDTO;
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
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public ResponseEntity<UserResponseDTO> addUser(@RequestBody User user,
			@RequestHeader("Authorization") String token) {
		log.info("Usuario " + user.getUserName() + " con Bearer: " + token.substring(7));
		user.setToken(token.substring(7));
		UserResponseDTO UserResponseDTO = userService.addUser(user);
		if (UserResponseDTO.getMessage().isEmpty()) {
			log.info("Usuario agregado exitosamente.");
			return new ResponseEntity<>(UserResponseDTO, HttpStatus.OK);
		} else {
			log.warn("No se pudo agregar el usuario");
			return new ResponseEntity<>(UserResponseDTO, HttpStatus.BAD_REQUEST);
		}
	}

}
