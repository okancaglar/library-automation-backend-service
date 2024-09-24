package pia.task.library_automation.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDTOPost {

	private int id;
	private String name;
	private String blurb;
	private double rentalPrice;
	private List<String> genres;
	private String writer;



	public BookDTOPost(int id, String name, String blurb, double rentalPrice, List<String> genres,
					   String writer) {
		this.id = id;
		this.name = name;
		this.blurb = blurb;
		this.rentalPrice = rentalPrice;
		this.genres = genres;
		this.writer = writer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}


}
