package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.Customer;

import java.util.List;

@Repository
public class CustomerDAOImp implements CustomerDAO{

	private EntityManager entityManager;

	@Autowired
	public CustomerDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public Customer getCustomerById(int id){

		String queryString = "select c from Customer c where c.id = :id";
		TypedQuery<Customer> query = entityManager.createQuery(queryString, Customer.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		}catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		String queryString = "select c from Customer c";
		TypedQuery<Customer> query = entityManager.createQuery(queryString, Customer.class);
		return query.getResultList();
	}

	@Override
	public void createCustomer(Customer customer) {
		entityManager.persist(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		entityManager.merge(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		entityManager.remove(customer);
	}
}
