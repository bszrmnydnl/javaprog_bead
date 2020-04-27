package konyvtarAdatbazis;

import java.util.Date;

public class Book {

	private boolean selected;
	private int id;
	private String author;
	private String title;
	private Date dateOfRelease;
	private String status;

	
	public Book() {
	}

	public Book(int id, String author, String title, Date dateOfRelease, String status) {
		super();
		this.selected = false;
		this.id = id;
		this.author = author;
		this.title = title;
		this.dateOfRelease = dateOfRelease;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOfRelease() {
		return dateOfRelease;
	}

	public void setDateOfRelease(Date dateOfRelease) {
		this.dateOfRelease = dateOfRelease;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
