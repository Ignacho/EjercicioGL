package com.example.ejercicio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ejercicio.domain.AuthRequest;
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
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Método encargado de validar la autenticación del usuario.
	 * 
	 * @param authRequest
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
			log.info("Autenticación exitosa!");
		} catch (Exception ex) {
			log.warn("username/password inválido");
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}

}
