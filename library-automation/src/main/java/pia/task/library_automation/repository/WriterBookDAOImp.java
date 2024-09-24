package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.WriterBook;


@Repository
public class WriterBookDAOImp implements WriterBookDAO{

	private EntityManager entityManager;


	@Autowired
	public WriterBookDAOImp(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	public WriterBook getWriterBook(int bookId) {

		String queryStr = "select w from WriterBook w where w.book.bookId = :bookId";
		TypedQuery<WriterBook> query = entityManager.createQuery(queryStr, WriterBook.class);
		query.setParameter("bookId", bookId);

		return query.getSingleResult();
	}

	@Override
	public String getWriterAsStr(int bookId) {
		return getWriterBook(bookId).getWriter();
	}

	@Override
	public void insertWriterBook(WriterBook writerBook) {
		entityManager.merge(writerBook);
		entityManager.flush();
	}

	@Override
	public void deleteWriterBook(WriterBook writerBook) {
		entityManager.remove(writerBook);
		entityManager.flush();
		entityManager.clear();
	}

	@Override
	public void updateWriterBook(WriterBook writerBook) {
		String queryStr = "delete from WriterBook w where w.book.bookId =:id";
		Query query = entityManager.createQuery(queryStr);
		query.setParameter("id", writerBook.getBook().getBookId());
		query.executeUpdate();

		entityManager.flush();

		entityManager.persist(writerBook);
		entityManager.flush();
	}

	@Override
	public void deleteWriterBookById(int id) {
		String queryStr = "delete from WriterBook w where w.book.bookId =:id";
		Query query = entityManager.createQuery(queryStr);
		query.setParameter("id", id);
		query.executeUpdate();

		entityManager.flush();
	}


}
