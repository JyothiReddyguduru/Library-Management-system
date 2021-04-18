package com.booknow.model;

public class ManageBookInput {
	
	private String name;
	
	private String isbn;
	
	private String author;
	
	private int copies;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	public ManageBookInput( String name, String isbn, String author, int copies) {
		
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.copies = copies;
	}

}
