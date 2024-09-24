package pia.task.library_automation.entity.compositekeys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class RentedBooksId implements Serializable {

	private int customer;
	private int book;
	private LocalDate rentalDate;


	public RentedBooksId() {
	}

	public RentedBooksId(int customerId, int bookId, LocalDate rentalDate) {
		this.customer = customerId;
		this.book = bookId;
		this.rentalDate = rentalDate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customer, book, rentalDate);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RentedBooksId that = (RentedBooksId) o;
		return customer == that.customer && book == that.book && Objects.equals(rentalDate, that.rentalDate);
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
}
