package com.example.ejercicio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entidad encargada de gestionar el mapeo de email y password al momento de autenticarse.
 * 
 * @author Ignacio Barberis
 * @since 08/06/2021
 * @version 1.0
 */
@Getter
@Setter
public class AuthRequest {
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9].*?[0-9]).{4,}$", message = "El formato del password no es correcto (una mayúscula, letras minúsculas, y dos números)")
	private String password;

	@NotNull(message = "Se tiene que ingresar el email")
	@NotBlank(message = "El email no puede estar vacío")
	@Pattern(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((.(\\w){2,3}))$", message = "Email inválido")
	private String email;
}
