package com.example.ejercicio.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

/**
 * DTO encargado de informar los datos requeridos en el response.
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponseDTO {

	private Integer id;
	private Timestamp created;
	private Timestamp modifier;
	private Timestamp lastLogin;
	private String token;
	private Boolean isActive;
}
