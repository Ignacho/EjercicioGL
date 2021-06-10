package com.example.ejercicio.mapper;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.example.ejercicio.domain.AuthRequest;
import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.UserResponseDTO;

/**
 * Mapper encargado de construir el UserResponseDTO.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Component
public class UserMapper {

	public UserResponseDTO fromUserTOUserResponseDTO(User user) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(user.getId());
		userResponseDTO.setCreated(user.getCreated());
		userResponseDTO.setModifier(user.getModifier());
		userResponseDTO.setLastLogin(user.getLastLogin());
		userResponseDTO.setToken(user.getToken());
		userResponseDTO.setIsActive(user.isActive());

		return userResponseDTO;
	}

	public User fromAuthRequestTOUser(AuthRequest authRequest, String token) {
		User user = new User();
		user.setPassword(authRequest.getPassword());
		user.setEmail(authRequest.getEmail());
		user.setToken(token);
		user.setActive(true);
		user.setLastLogin(new Timestamp(System.currentTimeMillis()));

		return user;
	}

}
