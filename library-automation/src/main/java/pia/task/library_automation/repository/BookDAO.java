package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.Book;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface BookDAO {

	Book findBookById(int id);
	List<Book> getAllBooks();
	List<Book> getAllBooksSortedByAddingDate();

	void createBook(Book book);
	void deleteBook(Book book);
	Book updateBook(Book book);

	void refreshBook(Book book);
	Book reattachBook(Book book);




}
