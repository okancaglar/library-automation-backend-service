package pia.task.library_automation.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "id_book")
	private int bookId;

	@Column(name = "name_book")
	private String bookName;

	@Column(name = "blurb_book")
	private String blurb;

	@Column(name = "rental_price_book")
	private double rentalPrice;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "adding_date_book")
	private LocalDate addingDate;

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private List<Genre> genres = new ArrayList<>();

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RentedBooks> rentedBooks;

	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private WriterBook writer;


	public Book(){}

	public Book(int bookId, String bookName, String blurb, double rentalPrice, LocalDate addingDate) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.blurb = blurb;
		this.rentalPrice = rentalPrice;
		this.addingDate = addingDate;
	}

	public int getBookId() {
		return bookId;
	}

	public WriterBook getWriter() {
		return writer;
	}

	public void setWriter(WriterBook writer) {
		this.writer = writer;
	}
 
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBlurb() {
		return blurb;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	public List<RentedBooks> getRentedBooks() {
		return rentedBooks;
	}

	public void setRentedBooks(List<RentedBooks> rentedBooks) {
		this.rentedBooks = rentedBooks;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public LocalDate getAddingDate() {
		return addingDate;
	}

	public void setAddingDate(LocalDate addingDate) {
		this.addingDate = addingDate;
	}
}
