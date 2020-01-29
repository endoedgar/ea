package cs544.exercise19;

import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Book {
	private int id;
	@NotNull
	@NotBlank
	private String title;
	@Pattern(regexp = "\\d{3}-\\d{10}")
	private String ISBN;
	@NotNull
	@NotBlank
	private String author;
	@DecimalMin(value = "0",inclusive = false)
	private double price;
	@Past
	private Date publishedDate;

	public Book() {
		super();
		publishedDate = new Date();
	}

	public Book(String title, String iSBN, String author, double price, Date publishedDate) {
		super();
		this.title = title;
		ISBN = iSBN;
		this.author = author;
		this.price = price;
		this.publishedDate = publishedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
}
