package pia.task.library_automation.service;

import pia.task.library_automation.entity.RentedBooks;

import java.util.List;

public interface RentedBooksService {
	List<RentedBooks> getRentedBooksByCustomerId(int customerId);
	List<RentedBooks> getRentedBooksByBookId(int bookId);
	RentedBooks getRentedBook(int customerId, int bookId);
	void rentBook(int bookId, int customerId);
	void returnBook(int bookId, int customerId);

}
