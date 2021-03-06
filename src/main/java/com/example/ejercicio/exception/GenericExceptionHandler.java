package com.example.ejercicio.exception;

import com.example.ejercicio.dto.ResponseDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Se encarga de nuclear el manejo de las excepciones de los endpoints en esta
 * clase.
 * 
 * @author Ignacio Barberis
 * @since 09/06/2021
 * @version 1.0
 */
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
		e.getBindingResult().getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
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

	@ExceptionHandler({ MalformedJwtException.class })
	public ResponseEntity<ResponseDTO> MalformedJwtException(MalformedJwtException e) {
		String message = e.getMessage();
		log.error(message, e);
		return new ResponseEntity<>(new ResponseDTO(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
