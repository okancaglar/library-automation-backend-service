package pia.task.library_automation.entity;

import jakarta.persistence.*;
import pia.task.library_automation.entity.compositekeys.GenreBookId;

@Entity
@Table(name = "book_genres")
@IdClass(GenreBookId.class)
public class Genre {

	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_book")
	private Book book;

	@Id
	@Column(name = "genre_name")
	private String genre;


	public Genre(){}

	public Genre(Book book, String genre) {
		this.book = book;
		this.genre = genre;
	}

	public Book getBook() {
		return book;
	}

	public void setBookId(Book book) {
		this.book = book;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
}
