package pia.task.library_automation.entity;


import jakarta.persistence.*;
import pia.task.library_automation.entity.compositekeys.WriterBookId;

@Entity
@Table(name = "writer_book")
@IdClass(WriterBookId.class)
public class WriterBook {

	@Id
	@Column(name = "writer_name")
	private String writer;

	@Id
	@OneToOne
	@JoinColumn(name = "book_id_book")
	private Book book;


	public WriterBook() {
	}

	public WriterBook(String writer, Book book) {
		this.writer = writer;
		this.book = book;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
