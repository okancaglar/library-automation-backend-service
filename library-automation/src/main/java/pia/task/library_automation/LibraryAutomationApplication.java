package pia.task.library_automation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pia.task.library_automation.entity.*;
import pia.task.library_automation.service.BookService;
import pia.task.library_automation.service.CustomerService;
import pia.task.library_automation.service.EmployeeService;
import pia.task.library_automation.service.RentedBooksService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class LibraryAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAutomationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(BookService bookService, CustomerService customerService,
											   EmployeeService employeeService, RentedBooksService rentedBooksService){
		return args -> {

			Book book = bookService.getBookById(1);
			System.out.println("Book name: " + book.getBookName());
			for(Genre genre : book.getGenres())
			{
				System.out.println("Book genre: " + genre.getGenre());
			}

			Customer customer = customerService.getCustomerById(1);
			System.out.println("customer Name-surname: "  + customer.getFirstName() + " " + customer.getLastName());
			for(RentedBooks rentedBook : customer.getRentedBooks())
			{
				System.out.println("Book Name: " + rentedBook.getBook().getBookName());
				System.out.println("Rental Date: " + rentedBook.getRentalDate().toString());
			}


			List<RentedBooks> rentedBooks = rentedBooksService.getRentedBooksByCustomerId(1);
			for (RentedBooks rentedBook : rentedBooks)
			{
				System.out.println("Customer Id: " + rentedBook.getCustomer().getId() + "\tbook name: " + rentedBook.getBook().getBookName());
			}

			/*List<String> genres = new ArrayList<>();
			genres.addAll(Arrays.asList("sci-fi", "action", "romance"));
			bookService.createBook(2, "Second Foundation", "Epic Story of humanity continue", 20.5,
					genres);*/
		};

	}
}
