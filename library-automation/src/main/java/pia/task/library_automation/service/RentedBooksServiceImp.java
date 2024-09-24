package pia.task.library_automation.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pia.task.library_automation.entity.RentedBooks;
import pia.task.library_automation.repository.BookDAO;
import pia.task.library_automation.repository.CustomerDAO;
import pia.task.library_automation.repository.RentedBooksDAO;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentedBooksServiceImp implements RentedBooksService{

	private RentedBooksDAO rentedBooksRepository;
	private BookDAO bookRepository;
	private CustomerDAO customerRepository;


	@Autowired
	public RentedBooksServiceImp(RentedBooksDAO rentedBooksRepository, BookDAO bookRepository, CustomerDAO customerRepository) {
		this.rentedBooksRepository = rentedBooksRepository;
		this.bookRepository = bookRepository;
		this.customerRepository = customerRepository;
	}


	@Override
	public List<RentedBooks> getRentedBooksByCustomerId(int customerId) {
		return rentedBooksRepository.getRentedBooksByCustomerId(customerId);
	}

	@Override
	public List<RentedBooks> getRentedBooksByBookId(int bookId) {
		return rentedBooksRepository.getRentedBooksByBookId(bookId);
	}

	@Override
	public RentedBooks getRentedBook(int customerId, int bookId) {
		return rentedBooksRepository.getRentedBook(customerId, bookId);
	}

	@Transactional
	@Override
	public void rentBook(int bookId, int customerId) {
		rentedBooksRepository.rentBook(new RentedBooks(LocalDate.now(), null, bookRepository.findBookById(bookId),
				customerRepository.getCustomerById(customerId)));
	}
	@Transactional
	@Override
	public void returnBook(int bookId, int customerId) {

		RentedBooks rentedBook = rentedBooksRepository.getRentedBook(customerId, bookId);
		rentedBook.setReturnDate(LocalDate.now());
		rentedBooksRepository.returnBook(rentedBook);
	}
}
