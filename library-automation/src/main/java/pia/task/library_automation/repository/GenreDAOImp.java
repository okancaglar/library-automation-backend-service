package pia.task.library_automation.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pia.task.library_automation.entity.Genre;

import java.util.List;

@Repository
public class GenreDAOImp implements GenreDAO{

	private EntityManager entityManager;

	@Autowired
	public GenreDAOImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void insertBooksGenre(Genre genre) {
		entityManager.persist(genre);
		entityManager.flush();
	}

	@Override
	public void updateGenres(List<Genre> genres) {

		String deleteQuery = "delete from Genre e where e.book.bookId =:id";
		Query query = entityManager.createQuery(deleteQuery);
		query.setParameter("id", genres.get(0).getBook().getBookId());
		query.executeUpdate();
		entityManager.flush();
		for(Genre genre: genres)
		{
			entityManager.persist(genre);
		}
	}

	@Override
	public void deleteGenres(List<Genre> genres) {
		for(Genre genre: genres)
		{
			entityManager.remove(genre);
		}
	}

	@Override
	public List<Genre> getGenresByBookId(int id) {
		String queryStr = "select g from Genre g where g.book.bookId =: id";
		TypedQuery<Genre> query = entityManager.createQuery(queryStr, Genre.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
