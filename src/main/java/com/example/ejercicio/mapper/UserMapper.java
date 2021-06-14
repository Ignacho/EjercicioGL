package com.example.ejercicio.mapper;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

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
		userResponseDTO.setIsActive(user.getIsActive());

		return userResponseDTO;
	}

}
