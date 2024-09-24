package pia.task.library_automation.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ExceptionResponseDTO {

	private String message;
	private int statusCode;
	private LocalDate date;

	public ExceptionResponseDTO(String message, int statusCode, LocalDate date) {
		this.message = message;
		this.statusCode = statusCode;
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
