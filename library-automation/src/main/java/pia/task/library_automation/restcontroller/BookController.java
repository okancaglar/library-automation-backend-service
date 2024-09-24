package pia.task.library_automation.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pia.task.library_automation.dto.BookDTO;
import pia.task.library_automation.dto.BookDTOPost;
import pia.task.library_automation.entity.Book;
import pia.task.library_automation.entity.Genre;
import pia.task.library_automation.entity.WriterBook;
import pia.task.library_automation.exceptionhandling.DataNotFoundException;
import pia.task.library_automation.service.BookService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* todo write exception handler for rest api end
* todo complete crud tasks for each api
* */

@RestController
@RequestMapping("/lib-auto/api/book")
public class BookController {

	private BookService bookService;

	@Autowired
	public BookController(BookService bookService)
	{
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public List<BookDTO> getBooks()
	{
		System.out.println("get books invoked at:" + new Date());
		List<BookDTO> responseObject = new ArrayList<>();
		List<Book> books = bookService.getAllBooks();

		System.out.println(books);
		for(Book book : books)
		{
			BookDTO bookDTO = new BookDTO(book.getBookId(), book.getBookName(), book.getBlurb(),
					book.getRentalPrice(), book.getAddingDate(), getGenresAsStringList(book.getGenres()), book.getWriter().getWriter());
			responseObject.add(bookDTO);
		}
		return responseObject;
	}

	@PostMapping("/create")
	public BookDTO saveBook(@RequestBody BookDTOPost bookDTOPost)
	{
		System.out.println("create invoked at:" + new Date());
		Book book = bookService.createAndFetch(bookDTOPost.getId(), bookDTOPost.getName(),
				bookDTOPost.getBlurb(), bookDTOPost.getRentalPrice(), bookDTOPost.getGenres(), bookDTOPost.getWriter());

		return new BookDTO(book.getBookId(), book.getBookName(), book.getBlurb(),
				book.getRentalPrice(), book.getAddingDate(), getGenresAsStringList(book.getGenres()),
				bookDTOPost.getWriter());
	}

	@GetMapping("/{id}")
	public BookDTO getBook(@PathVariable int id)
	{
		System.out.println("get invoked at:" + new Date());
		try
		{
			Book book = bookService.getBookById(id);
			return new BookDTO(book.getBookId(), book.getBookName(), book.getBlurb(), book.getRentalPrice(),
					book.getAddingDate(), getGenresAsStringList(book.getGenres()), book.getWriter().getWriter());
		}catch (Exception ex)
		{
			throw new DataNotFoundException("Given data object is not found for id " + id);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateBook(@RequestBody BookDTO updatedBook)
	{
		System.out.println("update invoked"+ new Date());
		try
		{
			/*Book book = bookService.getBookById(updatedBook.getId());
			book.setBookName(updatedBook.getName());
			book.setBlurb(updatedBook.getBlurb());
			book.setRentalPrice(updatedBook.getRentalPrice());
			book.setGenres(genresStrToObject(updatedBook.getGenres(), book));
			book.setWriter(new WriterBook(updatedBook.getWriter(), book));
			System.out.println(book.getBookId());
			System.out.println(book.getBookName());
			System.out.println(book.getAddingDate());
			System.out.println(book.getRentalPrice());
			System.out.println(book.getWriter().getWriter());
			Book fetchedBook = bookService.updateBook(book);*/
			//return new BookDTO(fetchedBook.getBookId(), fetchedBook.getBookName(), fetchedBook.getBlurb(), fetchedBook.getRentalPrice(),
			//		fetchedBook.getAddingDate(), getGenresAsStringList(fetchedBook.getGenres()), fetchedBook.getWriter().getWriter());

			bookService.updateBook(updatedBook.getId(), updatedBook.getName(),updatedBook.getBlurb(), updatedBook.getAddingDate(),
							updatedBook.getRentalPrice(), updatedBook.getGenres(), updatedBook.getWriter());
			return new ResponseEntity<>( HttpStatus.OK);

		}catch(Exception e){
			//todo exception handler
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable int id)
	{
		System.out.println("delete invoked at:" + new Date());
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<>( HttpStatus.OK);
		}catch (Exception e)
		{
			// todo exception handling
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	private List<String> getGenresAsStringList(List<Genre> genres)
	{
		ArrayList<String> strGenres = new ArrayList<>();
		for (Genre genre: genres)
		{
			strGenres.add(genre.getGenre());
		}
		return strGenres;
	}

	private List<Genre> genresStrToObject(List<String> genresStr, Book book)
	{
		List<Genre> genreList = new ArrayList<>();
		for(String genre: genresStr)
		{
			Genre genreObject = new Genre(book, genre);
			genreList.add(genreObject);
		}
		return genreList;
	}
}
