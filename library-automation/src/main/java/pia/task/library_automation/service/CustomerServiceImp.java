package pia.task.library_automation.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pia.task.library_automation.dto.BookDTO;
import pia.task.library_automation.entity.Customer;
import pia.task.library_automation.entity.RentedBooks;
import pia.task.library_automation.repository.CustomerDAO;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService{

	private CustomerDAO customerRepository;

	@Autowired
	public CustomerServiceImp(CustomerDAO customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer getCustomerById(int id) {
		return customerRepository.getCustomerById(id);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}


	@Transactional
	@Override
	public void createCustomer(int id, String firstName, String lastName) {
		customerRepository.createCustomer(new Customer(id, firstName, lastName, LocalDate.now()));
	}

	@Transactional
	@Override
	public void deleteCustomer(int id) {
		customerRepository.deleteCustomer(customerRepository.getCustomerById(id));
	}

	@Transactional
	@Override
	public void updateCustomer(int id, String firstName, String lastName) {
		Customer customer = customerRepository.getCustomerById(id);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customerRepository.updateCustomer(customer);
	}
}
