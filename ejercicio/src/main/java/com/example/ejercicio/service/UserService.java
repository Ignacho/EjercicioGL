package com.example.ejercicio.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.UserResponseDTO;
import com.example.ejercicio.mapper.UserResponseMapper;
import com.example.ejercicio.repository.UserRepository;

/**
 * Se encarga de la lógica de la gestión del User.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Service
public class UserService {
	private Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserResponseMapper userResponseMapper;

	/**
	 * Envía al controller todos los usuarios cargados.
	 * 
	 * @return List<User>
	 */
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	/**
	 * Envía al controller el UserResponseDTO a devolver.
	 * 
	 * @param user
	 * @return UserResponseDTO
	 */
	public UserResponseDTO addUser(User user) {
		if (mailValido(user.getEmail())) {
			log.info("Mail válido");
			userRepository.save(user);
			return userResponseMapper.fromUserTOUserResponseDTO(user, "");
		} else {
			log.info("Mail inválido");
			return userResponseMapper.fromUserTOUserResponseDTO(new User(), "invalid email");
		}
	}

	/**
	 * Valida que el mail sea válido mediante una RegExr
	 * 
	 * @param email
	 * @return Boolean
	 */
	public static Boolean mailValido(String email) {
		String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();

	}

}
