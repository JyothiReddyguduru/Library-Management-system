package com.booknow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booknow.entities.Book;
import com.booknow.model.ManageBookInput;
import com.booknow.services.BookNowService;

/*
 * Controller related to book end points.
 * @Author : Lasya Bentula
 * @Last Modified On 11/10/2020 
 */

@RestController
public class BookNowController {
	
	@Autowired
	BookNowService bookNowService;
		
	/**
	 * mthod to fetch all books from db
	 * @return
	 */
	@GetMapping("/books/all")
	public List<Book> getBooks() {
		return bookNowService.getBooks();
	}

	/**
	 * method to  add a book into db
	 * @param manageBookInput
	 */
	@PostMapping("/addBook")
	public void addBook( @RequestBody ManageBookInput manageBookInput) {
		 bookNowService.addBook(manageBookInput);
	}
	
	/**
	 * method to edit book details and add more copies in the db
	 * @param manageBookInput
	 */
	@PostMapping("/editBook")
	public void editBook( @RequestBody ManageBookInput manageBookInput) {
		 bookNowService.editBook(manageBookInput);
	}
	
}
