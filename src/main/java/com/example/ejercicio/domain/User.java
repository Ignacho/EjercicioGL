package com.example.ejercicio.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad User
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Getter
@Setter
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

	@NotNull(message = "Se tiene que ingresar el password")
	@NotBlank(message = "El password no puede estar vacío")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9].*?[0-9]).{4,}$", message = "El formato del password no es correcto (una mayúscula, letras minúsculas, y dos números)")
	private String password;

	@NotNull(message = "Se tiene que ingresar el email")
	@NotBlank(message = "El email no puede estar vacío")
	@Pattern(regexp = "^([\\w\\.\\-]+)@([\\w\\-]+)((.(\\w){2,3}))$", message = "Email inválido")
	@Column(updatable = false)
	private String email;

	@OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private List<Phone> phones;

	@CreationTimestamp
	private LocalDateTime created;

	@UpdateTimestamp
	private LocalDateTime modifier;

	@JsonProperty("last_login")
	private LocalDateTime lastLogin;
	private String token;

	@ColumnDefault("true")
	private Boolean isActive;
}
