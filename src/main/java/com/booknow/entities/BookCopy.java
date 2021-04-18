package com.booknow.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.booknow.entities.Enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Author : Lasya Bentula
 *  @Last Modified On 11/27/2020
 *  entity for book copies table. Has a forign key with book id
 *  primary key is id
 */
@Entity
@Table(name="book_copies")
public class BookCopy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@Column
	private String borrower;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	

}
