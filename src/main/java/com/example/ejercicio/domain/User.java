package com.example.ejercicio.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad User
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonProperty("name")
	private String userName;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9].*?[0-9]).{4,}$", message = "El formato del password no es correcto (una mayúscula, letras minúsculas, y dos números)")
	private String password;

	@NotNull(message = "Se tiene que ingresar el email")
	@NotBlank(message = "El email no puede estar vacío")
	@Pattern(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((.(\\w){2,3}))$", message = "Email inválido")
	private String email;

	@OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid", referencedColumnName = "id")
	private List<Phone> phones;

	@CreationTimestamp
	private Timestamp created;

	@UpdateTimestamp
	private Timestamp modifier;

	private Timestamp lastLogin;
	private String token;
	private Boolean isActive;
}
