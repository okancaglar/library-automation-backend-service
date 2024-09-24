package pia.task.library_automation.dto;

import java.time.LocalDate;
import java.util.List;

public class CustomerAlterationDTO {

	private int id;
	private String firstName;
	private String lastName;
	private LocalDate registrationDate;

	public CustomerAlterationDTO() {
	}

	public CustomerAlterationDTO(int id, String firstName, String lastName, LocalDate registrationDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
}
