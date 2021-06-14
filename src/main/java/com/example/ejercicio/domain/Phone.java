package com.example.ejercicio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entidad Phone
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "phones")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String number;
	private String citycode;
	private String countrycode;
	private Integer userId;
}
