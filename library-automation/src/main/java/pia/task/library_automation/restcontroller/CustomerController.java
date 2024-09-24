package pia.task.library_automation.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pia.task.library_automation.dto.BookDTO;
import pia.task.library_automation.dto.CustomerAlterationDTO;
import pia.task.library_automation.dto.CustomerGetDTO;
import pia.task.library_automation.entity.Book;
import pia.task.library_automation.entity.Customer;
import pia.task.library_automation.entity.Genre;
import pia.task.library_automation.entity.RentedBooks;
import pia.task.library_automation.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lib-auto/api/customer")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}


	@GetMapping("/{id}")
	public CustomerGetDTO getCustomer(@PathVariable int id)
	{
		try {
			Customer customer = customerService.getCustomerById(id);
			List<BookDTO> books = new ArrayList<>();

			for(RentedBooks book: customer.getRentedBooks())
			{
				books.add(new BookDTO(book.getBook().getBookId(), book.getBook().getBookName(),
						book.getBook().getBlurb(), book.getBook().getRentalPrice(), book.getBook().getAddingDate(),
						getGenresAsStringList(book.getBook().getGenres()), book.getBook().getWriter().getWriter()));
			}
			return new CustomerGetDTO(customer.getId(), customer.getFirstName(), customer.getLastName(),
					customer.getRegistrationDate(), books);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/customers")
	public List<CustomerGetDTO> getCustomers()
	{
		try {
			List<CustomerGetDTO> customers = new ArrayList<>();
			for(Customer customer: customerService.getAllCustomers())
			{
				List<BookDTO> books = new ArrayList<>();

				for(RentedBooks book: customer.getRentedBooks())
				{
					books.add(new BookDTO(book.getBook().getBookId(), book.getBook().getBookName(),
							book.getBook().getBlurb(), book.getBook().getRentalPrice(),
							book.getBook().getAddingDate(), getGenresAsStringList(book.getBook().getGenres()),
							book.getBook().getWriter().getWriter()));
				}
				customers.add(new CustomerGetDTO(customer.getId(), customer.getFirstName(), customer.getLastName(),
						customer.getRegistrationDate(), books));
			}
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/create")
	public String createCustomer(@RequestBody CustomerAlterationDTO newCustomer)
	{
		try {
			customerService.createCustomer(newCustomer.getId(), newCustomer.getFirstName(), newCustomer.getLastName());
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
