package pia.task.library_automation.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pia.task.library_automation.dto.BookDTO;
import pia.task.library_automation.dto.RentedBookGetDTO;
import pia.task.library_automation.dto.RentedBookPostDTO;
import pia.task.library_automation.entity.Genre;
import pia.task.library_automation.entity.RentedBooks;
import pia.task.library_automation.service.RentedBooksService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lib-auto/api/rented-books")
public class RentedBooksController {

	private RentedBooksService rentedBooksService;


	@Autowired
	public RentedBooksController(RentedBooksService rentedBooksService) {
		this.rentedBooksService = rentedBooksService;
	}


	@GetMapping("/get/{customerId}/{bookId}")
	public RentedBookGetDTO getRentedBook(@PathVariable int customerId, @PathVariable int bookId)
	{

		try {
			RentedBooks rentedBook = rentedBooksService.getRentedBook(customerId, bookId);
			return new RentedBookGetDTO(rentedBook.getCustomer().getId(), new BookDTO(rentedBook.getBook().getBookId(),
					rentedBook.getBook().getBookName(), rentedBook.getBook().getBlurb(), rentedBook.getBook().getRentalPrice(),
					rentedBook.getBook().getAddingDate(), getGenresAsStringList(rentedBook.getBook().getGenres()),
					rentedBook.getBook().getWriter().getWriter()), rentedBook.getRentalDate(),
					rentedBook.getReturnDate());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/get/all-books/{customerId}")
	public List<RentedBookGetDTO> getAllBooksByCustomerId(@PathVariable int customerId)
	{
		try {
			List<RentedBookGetDTO> returnList = new ArrayList<>();

			for(RentedBooks rentedBook: rentedBooksService.getRentedBooksByCustomerId(customerId))
			{
				returnList.add(new RentedBookGetDTO(rentedBook.getCustomer().getId(), new BookDTO(rentedBook.getBook().getBookId(),
						rentedBook.getBook().getBookName(), rentedBook.getBook().getBlurb(), rentedBook.getBook().getRentalPrice(),
						rentedBook.getBook().getAddingDate(), getGenresAsStringList(rentedBook.getBook().getGenres()),
						rentedBook.getBook().getWriter().getWriter()), rentedBook.getRentalDate(),
						rentedBook.getReturnDate()));
			}
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/rent")
	public String rentBook(@RequestBody RentedBookPostDTO rentedBookPostDTO)
	{
		try {
			rentedBooksService.rentBook(rentedBookPostDTO.getBookId(),
					rentedBookPostDTO.getCustomerId());
			return "operation is successful";
		} catch (Exception e) {
			e.printStackTrace();
			return "opration failed";
		}
	}

	@PutMapping("/return")
	public String returnBook(@RequestBody RentedBookPostDTO rentedBookPostDTO)
	{
		try {
			rentedBooksService.returnBook(rentedBookPostDTO.getBookId(), rentedBookPostDTO.getCustomerId());
			return "operation is successful";
		} catch (Exception e) {
			e.printStackTrace();
			return "operation is failed";
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


}
