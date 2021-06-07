package com.example.ejercicio.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.ejercicio.dto.ResponseDTO;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GenericExceptionHandler {
	private static final Logger log = LogManager.getLogger(GenericExceptionHandler.class);

	@ExceptionHandler({ ExistingMailException.class })
	public ResponseEntity<ResponseDTO> existingMailException(ExistingMailException e) {
		String message = e.getMessage();
		log.error(message, e);
		return new ResponseEntity<>(new ResponseDTO(message), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ResponseDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		log.error(message, e);
		return new ResponseEntity<>(new ResponseDTO(message), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ExpiredJwtException.class })
	public ResponseEntity<ResponseDTO> expiredJwtException(ExpiredJwtException e) {
		String message = e.getMessage();
		log.error(message, e);
		return new ResponseEntity<>(new ResponseDTO(message), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<ResponseDTO> runtimeException(RuntimeException e) {
		String message = e.getMessage();
		log.error(message, e);
		return new ResponseEntity<>(new ResponseDTO(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
