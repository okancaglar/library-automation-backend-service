package pia.task.library_automation.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDTO {

	private int id;
	private String name;
	private String blurb;
	private double rentalPrice;
	private LocalDate addingDate;
	private List<String> genres;
	private String writer;

	public BookDTO(int id, String name, String blurb, double rentalPrice, LocalDate addingDate, List<String> genres,
				   String writer) {
		this.id = id;
		this.name = name;
		this.blurb = blurb;
		this.rentalPrice = rentalPrice;
		this.addingDate = addingDate;
		this.genres = genres;
		this.writer = writer;
	}

	public BookDTO(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBlurb() {
		return blurb;
	}

	public void setBlurb(String blurb) {
		this.blurb = blurb;
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

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
}
