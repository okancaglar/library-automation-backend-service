package pia.task.library_automation.exceptionhandling;

import jakarta.persistence.EntityNotFoundException;

public class DataNotFoundException extends EntityNotFoundException{

	public DataNotFoundException(String message) {
		super(message);
	}
}
