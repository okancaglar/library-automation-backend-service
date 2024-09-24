package pia.task.library_automation.entity.compositekeys;

import java.io.Serializable;
import java.util.Objects;

public class GenreBookId implements Serializable {

	private int book;
	private String genre;

	public GenreBookId(){}

	public GenreBookId(int book, String genre) {
		this.book = book;
		this.genre = genre;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GenreBookId that = (GenreBookId) o;
		return book == that.book && Objects.equals(genre, that.genre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(book, genre);
	}
}
