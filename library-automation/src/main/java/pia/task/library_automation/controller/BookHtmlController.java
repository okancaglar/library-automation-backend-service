package pia.task.library_automation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pia.task.library_automation.dto.BookDTOHtml;
import pia.task.library_automation.entity.Book;
import pia.task.library_automation.entity.Genre;
import pia.task.library_automation.service.BookService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/lib-auto/book")
public class BookHtmlController {

	private BookService bookService;


	@Autowired
	public BookHtmlController(BookService bookService) {
		this.bookService = bookService;
	}


	@GetMapping("/")
	public String getBook(Model model)
	{
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "book";
	}

	@GetMapping("/add")
	public String addBookForm(Model model)
	{
		model.addAttribute("book", new BookDTOHtml());
		return "add-book";
	}

	@PostMapping("/add")
	public String createBook(@ModelAttribute BookDTOHtml book, Model model)
	{
		List<String> genres = Arrays.stream(book.getGenres().split(",")).toList();

		bookService.createBook(book.getId(), book.getName(), book.getBlurb(),
				book.getRentalPrice(), genres, book.getWriter());

		model.addAttribute("books", bookService.getAllBooks());
		return "redirect:/lib-auto/book/";
	}

	@GetMapping("/edit/{id}")
	public String editBookForm(@PathVariable int id, Model model)
	{
		model.addAttribute("book", createBookDTOHtml(bookService.getBookById(id)));
		return "edit-book";
	}

	/*
	@PostMapping("/edit/save")
	public String updateBook(@ModelAttribute BookDTOHtml bookDTOHtml)
	{
		Book book = bookService.getBookById(bookDTOHtml.getId());
		book.setBookName(bookDTOHtml.getName());
		book.setBlurb(bookDTOHtml.getBlurb());
		book.setRentalPrice(bookDTOHtml.getRentalPrice());
		book.setWriter();
	}
*/
	private BookDTOHtml createBookDTOHtml(Book book)
	{
		return new BookDTOHtml(book.getBookId(), book.getBookName(),
				book.getBlurb(), book.getRentalPrice(), book.getAddingDate(),
				genresToString(book.getGenres()), book.getWriter().getWriter());
	}

	private String genresToString(List<Genre> genres)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(Genre genre:genres)
		{
			stringBuilder.append(genre.getGenre() + ",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}


}
