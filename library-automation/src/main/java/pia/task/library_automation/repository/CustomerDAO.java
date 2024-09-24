package pia.task.library_automation.repository;

import ch.qos.logback.core.encoder.EchoEncoder;
import pia.task.library_automation.entity.Customer;

import java.util.List;

public interface CustomerDAO {
	Customer getCustomerById(int id);
	List<Customer> getAllCustomers();
	void createCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(Customer customer);

}
