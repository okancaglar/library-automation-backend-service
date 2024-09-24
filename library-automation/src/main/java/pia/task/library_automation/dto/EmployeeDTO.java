package pia.task.library_automation.dto;

import java.time.LocalDate;

public class EmployeeDTO {

	private int id;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate registrationDate;
	private double dailyWage;
	private String role;


	public EmployeeDTO(int id, String password, String firstName, String lastName, LocalDate registrationDate, double dailyWage, String role) {
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.dailyWage = dailyWage;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public double getDailyWage() {
		return dailyWage;
	}

	public void setDailyWage(double dailyWage) {
		this.dailyWage = dailyWage;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
