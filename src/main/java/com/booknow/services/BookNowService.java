package com.booknow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booknow.entities.Book;
import com.booknow.repositories.BookRepository;

@Service
public class BookNowService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> getBooks() {
		return bookRepository.findAll();
		
	}
	
	public Book addEditBook(Book book) {
		if(book.getId() == null) {
			//add book
		} else {
			///edit book
		}
		
		Book book = bookRepository.findByIsbn("");
		if(book != null) {
			//isbn duplicate
			throw exception();
		} else {
			bookRepository.save(book);
		}
		bookRepository.save(book);
		return book;
	}
}
