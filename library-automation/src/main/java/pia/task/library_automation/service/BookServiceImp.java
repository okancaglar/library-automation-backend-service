package pia.task.library_automation.service;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pia.task.library_automation.entity.Book;
import pia.task.library_automation.entity.Genre;
import pia.task.library_automation.entity.WriterBook;
import pia.task.library_automation.exceptionhandling.InvalidDataIdException;
import pia.task.library_automation.repository.BookDAO;
import pia.task.library_automation.repository.GenreDAO;
import pia.task.library_automation.repository.WriterBookDAO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImp implements BookService{

	private BookDAO bookRepository;
	private GenreDAO genreRepository;
	private WriterBookDAO writerBookRepository;

	@Autowired
	public BookServiceImp(BookDAO repository, GenreDAO genreRepository, WriterBookDAO writerBookRepository) {
		this.bookRepository = repository;
		this.genreRepository = genreRepository;
		this.writerBookRepository = writerBookRepository;
	}

	@Transactional
	@Override
	public Book getBookById(int id) {
		return bookRepository.findBookById(id);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.getAllBooks();
	}



	@Override
	public List<Book> getAllBooksSortedByAddingDate() {
		return bookRepository.getAllBooksSortedByAddingDate();
	}

	@Transactional
	@Override
	public void createBook(int id, String name, String blurb, double rentalPrice, List<String> genresStr, String writer) {

		try {
			Book book = new Book(id, name, blurb, rentalPrice, java.time.LocalDate.now());
			bookRepository.createBook(book);

			for (String genreStr : genresStr) {
				Genre genre = new Genre(book, genreStr);
				genreRepository.insertBooksGenre(genre);
			}

			writerBookRepository.insertWriterBook(new WriterBook(writer, book));
		}catch (DataIntegrityViolationException e)
		{
			throw new InvalidDataIdException("Data is already exist");
		}
	}

	@Transactional
	@Override
	public Book createAndFetch(int id, String name, String blurb, double rentalPrice, List<String> genresStr, String writer) {

		try {
			Book book = new Book(id, name, blurb, rentalPrice, java.time.LocalDate.now());
			bookRepository.createBook(book);

			for (String genreStr : genresStr) {
				Genre genre = new Genre(book, genreStr);
				genreRepository.insertBooksGenre(genre);
			}

			writerBookRepository.insertWriterBook(new WriterBook(writer, book));
			bookRepository.refreshBook(book);
			return bookRepository.findBookById(id);

		}catch (DataIntegrityViolationException e)
		{
			throw new InvalidDataIdException("Data is already exist");
		}
	}

	@Transactional
	@Override
	public Book updateBook(Book book) {

		try {
			//writerBookRepository.updateWriterBook(book.getWriter());
			//genreRepository.updateGenres(book.getGenres());

			return bookRepository.updateBook(book);
		} catch (DataIntegrityViolationException e) {
			throw new InvalidDataIdException("Invalid Data Field");
		}
	}

	@Transactional
	@Override
	public void deleteBook(int id) {
		System.out.println("inside deleteBook method in bookservice" + id);
		Book book = bookRepository.findBookById(id);

		genreRepository.deleteGenres(book.getGenres());
		writerBookRepository.deleteWriterBook(writerBookRepository.getWriterBook(id));
		bookRepository.deleteBook(bookRepository.findBookById(id));


	}

	@Transactional
	@Override
	public void updateBook(int id, String bookName, String blurb, LocalDate addingDate, double rentalPrice, List<String> genres, String writer) {
		Book book = getBookById(id);
		book.setBookName(bookName);
		book.setBlurb(blurb);
		book.setAddingDate(addingDate);
		book.setRentalPrice(rentalPrice);

		genreRepository.deleteGenres(genreRepository.getGenresByBookId(id));
		for(String genre: genres)
		{
			genreRepository.insertBooksGenre(new Genre(book, genre));
		}

		writerBookRepository.deleteWriterBookById(id);

		writerBookRepository.insertWriterBook(new WriterBook(writer, book));

		bookRepository.updateBook(book);

	}
}
