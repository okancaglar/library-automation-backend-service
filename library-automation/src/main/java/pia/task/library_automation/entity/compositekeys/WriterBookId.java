package pia.task.library_automation.entity.compositekeys;

import java.io.Serializable;
import java.util.Objects;

public class WriterBookId implements Serializable {

	private String writer;
	private int book;

	public WriterBookId() {
	}

	public WriterBookId(String writer, int book) {
		this.writer = writer;
		this.book = book;
	}


	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getBook() {
		return book;
	}

	public void setBook(int book) {
		this.book = book;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WriterBookId that = (WriterBookId) o;
		return book == that.book && Objects.equals(writer, that.writer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(writer, book);
	}
}
