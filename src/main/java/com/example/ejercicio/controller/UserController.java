package com.example.ejercicio.controller;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.ResponseDTO;
import com.example.ejercicio.dto.UserResponseDTO;
import com.example.ejercicio.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/qtyUserActive")
	public ResponseDTO getQtyUserActive() {
		return new ResponseDTO("Cantidad de usuarios activos: " + this.getUsers().stream().filter(user -> user.getIsActive().equals(true)).count());
	}
	
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
