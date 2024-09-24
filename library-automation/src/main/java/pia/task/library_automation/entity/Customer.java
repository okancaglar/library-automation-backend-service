package pia.task.library_automation.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@Column(name = "id_customer")
	private int id;

	@Column(name = "first_name_customer")
	private String firstName;

	@Column(name = "last_name_customer")
	private String lastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "registiration_date_customer")
	private LocalDate registrationDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<RentedBooks> rentedBooks = new ArrayList<>();


	public Customer(){}

	public Customer(int id, String firstName, String lastName, LocalDate registrationDate, List<RentedBooks> rentedBooks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationDate = registrationDate;
		this.rentedBooks = rentedBooks;
	}

	public Customer(int id, String firstName, String lastName, LocalDate registrationDate) {
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

	public List<RentedBooks> getRentedBooks() {
		return rentedBooks;
	}

	public void setRentedBooks(List<RentedBooks> rentedBooks) {
		this.rentedBooks = rentedBooks;
	}
}
