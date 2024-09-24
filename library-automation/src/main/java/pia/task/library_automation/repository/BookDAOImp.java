package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import pia.task.library_automation.entity.Book;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public class BookDAOImp implements BookDAO{

	private EntityManager entityManager;

	@Autowired
	public BookDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Book findBookById(int id) {

		String queryString = "select b from Book b where b.bookId = :id";
		TypedQuery<Book> query = entityManager.createQuery(queryString, Book.class);
		query.setParameter("id", id);

		return query.getSingleResult();

	}

	@Override
	public List<Book> getAllBooks() {

		String queryString = "select b from Book b";
		TypedQuery<Book> query = entityManager.createQuery(queryString, Book.class);

		try {
			return query.getResultList();
		}catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public List<Book> getAllBooksSortedByAddingDate() {
		String queryString = "select b from Book b order by b.addingDate";
		TypedQuery<Book> query = entityManager.createQuery(queryString, Book.class);

		try {
			return query.getResultList();
		}catch(Exception e)
		{
			return null;
		}
	}

	@Override
	public void createBook(Book book) {
		entityManager.persist(book);
		entityManager.flush();
	}

	@Override
	public void deleteBook(Book book) {
		entityManager.remove(book);
		/*String queryStr = "delete from Book b where bookId =: id";
		Query query = entityManager.createQuery(queryStr);
		query.setParameter("id", book.getBookId());
		query.executeUpdate();*/
		entityManager.flush();
	}

	@Override
	public Book updateBook(Book book) {
		return entityManager.merge(book);


	}

	@Override
	public void refreshBook(Book book) {
		entityManager.refresh(book);
	}

	@Override
	public Book reattachBook(Book book) {
		return entityManager.merge(book);
	}

}
