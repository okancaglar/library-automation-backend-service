package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.RentedBooks;

import java.util.List;

@Repository
public class RentedBooksImp implements RentedBooksDAO{

	private EntityManager entityManager;

	@Autowired
	public RentedBooksImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<RentedBooks> getRentedBooksByCustomerId(int customerId) {

		String queryString = "select r from RentedBooks r where r.customer.id = :customerId";
		TypedQuery<RentedBooks> query = entityManager.createQuery(queryString, RentedBooks.class);
		query.setParameter("customerId", customerId);

		return query.getResultList();
	}

	@Override
	public List<RentedBooks> getRentedBooksByBookId(int bookId) {
		String queryString = "select r from RentedBooks r where r.book.bookId = :bookId";
		TypedQuery<RentedBooks> query = entityManager.createQuery(queryString, RentedBooks.class);
		query.setParameter("bookId", bookId);

		return query.getResultList();
	}

	@Override
	public RentedBooks getRentedBook(int customerId, int bookId) {

		String queryString = "select r from RentedBooks r where r.book.bookId = :bookId and r.customer.id = :customerId";
		TypedQuery<RentedBooks> query = entityManager.createQuery(queryString, RentedBooks.class);
		query.setParameter("bookId", bookId);
		query.setParameter("customerId", customerId);

		return query.getSingleResult();

	}

	@Override
	public void rentBook(RentedBooks rentedBook) {
		entityManager.persist(rentedBook);
	}

	@Override
	public void returnBook(RentedBooks rentedBook) {
		entityManager.merge(rentedBook);
	}

	@Override
	public void deleteRentedBook(RentedBooks rentedBook) {
		entityManager.remove(rentedBook);
	}

}
