package pia.task.library_automation.repository;

import pia.task.library_automation.entity.Customer;
import pia.task.library_automation.entity.RentedBooks;

import java.util.List;

public interface RentedBooksDAO {

	List<RentedBooks> getRentedBooksByCustomerId(int customerId);
	List<RentedBooks> getRentedBooksByBookId(int bookId);
	RentedBooks getRentedBook(int customerId, int bookId);
	void rentBook(RentedBooks rentedBook);
	void returnBook(RentedBooks rentedBook);
	void deleteRentedBook(RentedBooks rentedBook);
}
