package pia.task.library_automation.repository;

import pia.task.library_automation.entity.Book;
import pia.task.library_automation.entity.Genre;

import java.util.List;

public interface GenreDAO {

	void insertBooksGenre(Genre genre);
	void updateGenres(List<Genre> genres);
	void deleteGenres(List<Genre> genres);
	List<Genre> getGenresByBookId(int id);

}
