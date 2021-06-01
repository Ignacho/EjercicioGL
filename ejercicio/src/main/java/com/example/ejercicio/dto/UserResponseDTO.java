package com.example.ejercicio.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO encargado de informar los datos requeridos en el response.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserResponseDTO {

	private Integer id;
	private Timestamp created;
	private Timestamp modifier;
	private Timestamp lastLogin;
	private String token;
	private Boolean isActive;
	private String message;
}
