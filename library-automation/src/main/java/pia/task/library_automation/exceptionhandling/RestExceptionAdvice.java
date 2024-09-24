package pia.task.library_automation.exceptionhandling;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pia.task.library_automation.dto.ExceptionResponseDTO;

import java.time.LocalDate;

@ControllerAdvice
public class RestExceptionAdvice {



	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionResponseDTO> entityNotFoundExceptionHandler(EntityNotFoundException ex)
	{
		ExceptionResponseDTO response = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(),
				LocalDate.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(InvalidDataIdException.class)
	public ResponseEntity<ExceptionResponseDTO> invalidDataIdExceptionHandler(InvalidDataIdException ex)
	{
		ExceptionResponseDTO response = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value(),
				LocalDate.now());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}



}
