package pia.task.library_automation.service;

import org.springframework.stereotype.Service;
import pia.task.library_automation.entity.Book;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface BookService {

	Book getBookById(int id);
	List<Book> getAllBooks();
	List<Book> getAllBooksSortedByAddingDate();
	void createBook(int id, String name, String blurb, double rentalPrice, List<String> genres, String writer);
	Book createAndFetch(int id, String name, String blurb, double rentalPrice, List<String> genres, String writer);
	Book updateBook(Book book);
	void deleteBook(int id);
	void updateBook(int id, String bookName, String blurb, LocalDate addingDate,
					double rentalPrice, List<String> genres, String writer);

}
