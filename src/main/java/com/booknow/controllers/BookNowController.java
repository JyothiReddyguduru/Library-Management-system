package com.booknow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booknow.entities.Book;
import com.booknow.services.BookNowService;

/*
 * @Author - Jyothi Reddy Guduru*/
@RestController
public class BookNowController {
	
	@Autowired
	BookNowService bookNowService;
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		return bookNowService.getBooks();
	}
	
}
