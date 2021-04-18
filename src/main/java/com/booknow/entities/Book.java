package com.booknow.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**Author : Jyothi Reddy
 * entity for books table or book model
 * each book has a one to many with book copies**/
@Entity
@Table(name="books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@Column(unique=true)
	private String isbn;
	
	@Column
	private String author;
	
	@OneToMany(mappedBy="book")
	private List<BookCopy> copies;

	public Book() {
		super();
	}


	public Book(Long id, String name, String isbn, String author, List<BookCopy> copies) {
		super();
		this.id = id;
		this.name = name;
		this.isbn = isbn;
		this.author = author;
		this.copies = copies;
	}

	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public List<BookCopy> getCopies() {
		return copies;
	}


	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
