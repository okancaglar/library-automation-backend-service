package pia.task.library_automation.dto;

import java.time.LocalDate;

public class RentedBookGetDTO {

	private int customerId;
	private BookDTO book;
	private LocalDate rentalDate;
	private LocalDate returnDate;

	public RentedBookGetDTO() {
	}

	public RentedBookGetDTO(int customerId, BookDTO book, LocalDate rentalDate, LocalDate returnDate) {
		this.customerId = customerId;
		this.book = book;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public BookDTO getBook() {
		return book;
	}

	public void setBook(BookDTO book) {
		this.book = book;
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
}
