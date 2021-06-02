package com.example.ejercicio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ejercicio.domain.Phone;
import com.example.ejercicio.domain.User;
import com.example.ejercicio.repository.UserRepository;

@SpringBootApplication
public class EjercicioApplication {
	@Autowired
	private UserRepository repository;
	
	@PostConstruct
	public void initUsers() {
	    List<User> users = Stream.of(
				new User(1, "ignacio", "password", "ignachobarberis@gmail.com", new ArrayList<Phone>(),
						new Timestamp(System.currentTimeMillis()), null, new Timestamp(System.currentTimeMillis()),
						null, null)
	    ).collect(Collectors.toList());
	    repository.saveAll(users);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EjercicioApplication.class, args);
	}

}
