package pia.task.library_automation.exceptionhandling;

import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;

public class InvalidDataIdException extends DataIntegrityViolationException {
	public InvalidDataIdException(String message) {
		super(message);
	}
}
