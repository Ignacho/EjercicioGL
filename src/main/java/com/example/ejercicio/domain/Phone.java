package com.example.ejercicio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad Phone
 * 
 * @author Ignacio Barberis
 * @since 01/06/2021
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String number;
	private String citycode;
	private String countrycode;
}
