package com.booknow.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booknow.entities.BookHistory;
import com.booknow.services.BookNowService;

/*
 * @Author : Lasya Bentula
 * @Last Modified On 11/27/2020 */
@RestController
public class BookCopyController {

	@Autowired
	BookNowService bookNowService;
	/**
	 * method to fetch all issued books for return page
	 * @return list of book history
	 */
	@GetMapping("/books/issued")
    public List<BookHistory> getAllIssuedBooks() {
		return bookNowService.getAllIssuedBooks();
	}
	
	/**
	 * method to issue a book to a given username
	 * @param data
	 */
	@RequestMapping(value = "/issueBook", method = RequestMethod.POST)
	public void issueBook(@RequestBody Map<String, String> data) {
		String username = data.get("username");
		String isbn = data.get("isbn");
		bookNowService.issueBook(username, isbn);
	}
	
	/**
	 * method to return book and calculate fines
	 * @param data
	 */
	@RequestMapping(value = "/returnBook", method = RequestMethod.POST)
	public void returnBook(@RequestBody Map<String, String> data) {
		String username = data.get("username");
		String isbn = data.get("isbn");
		bookNowService.returnBook(username, isbn);
	}
	
	/**
	 * method to calculate fines
	 * @param username
	 * @param isbn
	 * @return
	 */
	@GetMapping("/bookCopy/fine")
	public Double getFineForBookCopy(@RequestParam(value = "username") String username, 
			@RequestParam(value = "isbn") String isbn) {
		return bookNowService.calculateFineAmount(username, isbn);
	}
}
