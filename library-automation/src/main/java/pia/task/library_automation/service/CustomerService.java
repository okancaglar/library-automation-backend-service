package pia.task.library_automation.service;

import pia.task.library_automation.dto.BookDTO;
import pia.task.library_automation.entity.Customer;

import java.util.List;

public interface CustomerService {
	Customer getCustomerById(int id);
	List<Customer> getAllCustomers();
	void createCustomer(int id, String firstName, String lastName);
	void deleteCustomer(int id);
	void updateCustomer(int id, String firstName, String lastName);
}
