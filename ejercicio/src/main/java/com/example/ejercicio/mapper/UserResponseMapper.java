package com.example.ejercicio.mapper;

import org.springframework.stereotype.Component;

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
public class UserResponseMapper {

	public UserResponseDTO fromUserTOUserResponseDTO(User user, String message) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setId(user.getId());
		userResponseDTO.setCreated(user.getCreated());
		userResponseDTO.setModifier(user.getModifier());
		userResponseDTO.setLastLogin(user.getLastLogin());
		userResponseDTO.setToken(user.getToken());
		userResponseDTO.setIsActive(user.getIsActive());
		userResponseDTO.setMessage(message);

		return userResponseDTO;
	}

}
