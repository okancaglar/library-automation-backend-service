package pia.task.library_automation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import pia.task.library_automation.entity.compositekeys.RentedBooksId;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "rented_books")
@IdClass(RentedBooksId.class)
public class RentedBooks {

	@Id
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column(name = "rental_date")
	private LocalDate rentalDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "return_date")
	private LocalDate returnDate;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_book")
	private Book book;

	@Id
	@ManyToOne
	@JoinColumn(name = "customer_id_customer")
	private Customer customer;


	public RentedBooks(){}

	public RentedBooks(LocalDate rentalDate, LocalDate returnDate, Book book, Customer customer) {
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.book = book;
		this.customer = customer;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
