package com.booknow.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.booknow.entities.Enums.Status;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author : Lasya Bentula
 * @Last Modified On 11/27/2020
 * Entity to store all book history.
 * One record for each book copy and username combination
 */
@Entity
@Table(name="book_history")
public class BookHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String isbn;
	
	@Column
	private String author;
	
	@Column
	private Long bookId;
	
	@Column
	private Long bookCopyId;
	
	@Column
	private Date action_date;
	
	@Column
	private String action_by;
	
	@Column
	private Double fine;
	
	@Column
	private String borrower;
	
	@Column
	private Date due_date;
	
	@Column
	private Date returnDate;
	

	public BookHistory() {
		super();
	}

	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BookHistory(Long id, String name, Status status, String isbn, String author, Long bookId, Long bookCopyId,
			Date action_date, String action_by, Double fine, String borrower, Date due_date, Date returnDate) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.isbn = isbn;
		this.author = author;
		this.bookId = bookId;
		this.bookCopyId = bookCopyId;
		this.action_date = action_date;
		this.action_by = action_by;
		this.fine = fine;
		this.borrower = borrower;
		this.due_date = due_date;
		this.returnDate = returnDate;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	public Long getBookId() {
		return bookId;
	}



	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}



	public Long getBookCopyId() {
		return bookCopyId;
	}



	public void setBookCopyId(Long bookCopyId) {
		this.bookCopyId = bookCopyId;
	}



	public Date getAction_date() {
		return action_date;
	}

	public void setAction_date(Date action_date) {
		this.action_date = action_date;
	}

	public String getAction_by() {
		return action_by;
	}

	public void setAction_by(String action_by) {
		this.action_by = action_by;
	}

	public Double getFine() {
		return fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

}
