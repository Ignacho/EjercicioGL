package com.example.ejercicio.controller;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.mapper.UserMapper;
import com.example.ejercicio.service.UserService;
import com.example.ejercicio.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@RestController
@Slf4j
public class Authenticate {
	@Autowired
	private UserService userService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Método encargado de validar la creación y autenticación del usuario.
	 * 
	 * @param user
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public String registerAndGetToken(@Valid @RequestBody User user) throws Exception {
		User userByEmail = userService.findByEmail(user.getEmail());
		if (Objects.isNull(userByEmail)) {
			String token = jwtUtil.generateToken(user.getEmail());
			user.setIsActive(Objects.isNull(user.getIsActive()) ? true : user.getIsActive());
			user.setToken(token);
			userService.addUser(user);
			log.info("Nuevo usuario generado");
			return token;
		} else {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
			userService.updLastLogin(userByEmail.getId(), new Timestamp(System.currentTimeMillis()));
			log.info("Existe mail {} con token {}", user.getEmail(), userByEmail.getToken());
			return userByEmail.getToken();
		}
	}

}
