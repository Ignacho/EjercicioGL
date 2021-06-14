package com.example.ejercicio.service;

import com.example.ejercicio.domain.Phone;
import com.example.ejercicio.domain.User;
import com.example.ejercicio.dto.UserResponseDTO;
import com.example.ejercicio.exception.ExistingMailException;
import com.example.ejercicio.mapper.UserMapper;
import com.example.ejercicio.repository.UserPhoneRepository;
import com.example.ejercicio.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Se encarga de la lógica de la gestión del User.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserPhoneRepository userPhoneRepository;

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userRepository.save(user);
				log.info("Usuario agregado con id {}", user.getId());
				return userMapper.fromUserTOUserResponseDTO(user);
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
		if (Objects.isNull(user.getId())) {
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
					currentUser.setUserName(user.getUserName());
					currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
					currentUser.setEmail(user.getEmail());
					if (Objects.nonNull(user.getIsActive()))
						currentUser.setIsActive(user.getIsActive());

					List<Phone> listPhones = userPhoneRepository.findByUserId(user.getId());
					if (!listPhones.isEmpty()) {
						log.info("Teléfono eliminados {}", listPhones.toString());
						userPhoneRepository.deleteByUserId(user.getId());
					}
					currentUser.getPhones().clear();
					currentUser.setPhones(user.getPhones());
					User userUpd = userRepository.save(currentUser);
					log.info("Usuario: {} actualizado exitosamente", user.getUserName());
					return userMapper.fromUserTOUserResponseDTO(userUpd);
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

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void updLastLogin(Integer id, Timestamp lastLogin) {
		userRepository.updateUserLastLogin(id, lastLogin);
	}
		
}
