package com.example.ejercicio.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.UserResponseDTO;
import com.example.ejercicio.exception.ExistingMailException;
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
	 * Agrega un usuario.
	 * 
	 * @param user
	 * @return UserResponseDTO
	 * @throws Exception
	 */
	public UserResponseDTO addUser(User user) throws Exception {
		if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
			log.info("El correo ya registrado");
			throw new ExistingMailException("El correo ya registrado");
		} else {
			try {
				userRepository.save(user);
				log.info("Usuario agregado exitosamente.");
				return userResponseMapper.fromUserTOUserResponseDTO(user);
			} catch (RuntimeException ex) {
				log.error("Error al agregar usuario", ex);
				throw new RuntimeException("Error al agregar usuario");
			}
		}
	}

	/**
	 * Actualiza un usuario.
	 * 
	 * @param user
	 * @return UserResponseDTO
	 */
	public UserResponseDTO updUser(User user) {
		if (Objects.isNull(user) || Objects.isNull(user.getId())) {
			log.error("Falta ingresar el campo id de usuario");
			throw new RuntimeException("Ingrese un id de usuario");
		} else {
			Optional<User> userOpt = userRepository.findById(user.getId());
			if (!userOpt.isPresent()) {
				log.error("El id de usuario no se encuentra en el sistema");
				throw new RuntimeException("El usuario " + user.getId() + " no se encuentra en el sistema");
			} else {
				try {
					User currentUser = userOpt.get();
					if (Objects.nonNull(user.getUserName()))
						currentUser.setUserName(user.getUserName());

					if (Objects.nonNull(user.getPassword()))
						currentUser.setPassword(user.getPassword());

					if (Objects.nonNull(user.getEmail()))
						currentUser.setEmail(user.getEmail());

					currentUser.setPhones(user.getPhones());

					User userUpd = userRepository.save(currentUser);
					log.info("Usuario: {} actualizado exitosamente", user.getUserName());
					return userResponseMapper.fromUserTOUserResponseDTO(userUpd);
				} catch (Exception ex) {
					log.error("Error al actualzar usuario: {}", user.getUserName());
					throw new RuntimeException("Error al actualzar usuario");
				}
			}
		}
	}

	/**
	 * Elimina un usuario.
	 * 
	 * @param id
	 * @return void
	 */
	public void delUser(Integer id) {
		try {
			userRepository.deleteById(id);
			log.info("Usuario: {} eliminado correctamente", id);
		} catch (EmptyResultDataAccessException ne) {
			log.error("Usuario: {} inexistente", id, ne);
			throw new EmptyResultDataAccessException("Usuario inexistente", id);
		} catch (Exception ex) {
			log.error("Error al eliminar el usuario: {}", id, ex);
			throw new RuntimeException("Error al eliminar usuario");
		}
	}

}
