package com.example.ejercicio.controller;

import java.sql.Timestamp;
import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.domain.AuthRequest;
import com.example.ejercicio.domain.User;
import com.example.ejercicio.mapper.UserMapper;
import com.example.ejercicio.service.UserService;
import com.example.ejercicio.util.JwtUtil;

/**
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@RestController
public class Authenticate {
	private Logger log = LoggerFactory.getLogger(Authenticate.class);

	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * Método encargado de validar la creación y autenticación del usuario.
	 * 
	 * @param AuthRequest
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public String registerAndGetToken(@Valid @RequestBody AuthRequest authRequest) throws Exception {
		User userByEmail = userService.findByEmail(authRequest.getEmail());
		if (Objects.isNull(userByEmail)) {
			String token = jwtUtil.generateToken(authRequest.getEmail());
			userService.addUser(userMapper.fromAuthRequestTOUser(authRequest, token));
			log.info("Nuevo usuario generado");
			return token;
		} else {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
			userService.updLastLogin(userByEmail.getId(), new Timestamp(System.currentTimeMillis()));
			log.info("Existe mail {} con token {}", authRequest.getEmail(), userByEmail.getToken());
			return userByEmail.getToken();
		}
	}

}
