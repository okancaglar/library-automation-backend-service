package pia.task.library_automation.dto;

public class RentedBookPostDTO {

	private int customerId;
	private int bookId;

	public RentedBookPostDTO() {
	}

	public RentedBookPostDTO(int customerId, int bookId) {
		this.customerId = customerId;
		this.bookId = bookId;
	}


	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
