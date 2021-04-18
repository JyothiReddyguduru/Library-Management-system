package com.booknow.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booknow.entities.Book;
import com.booknow.entities.BookCopy;
import com.booknow.entities.BookHistory;
import com.booknow.entities.Enums;
import com.booknow.entities.Enums.Status;
import com.booknow.entities.User;
import com.booknow.model.ManageBookInput;
import com.booknow.repositories.BookCopyRepository;
import com.booknow.repositories.BookHistoryRepository;
import com.booknow.repositories.BookLimitRepository;
import com.booknow.repositories.BookRepository;
import com.booknow.repositories.FinePolicyRepository;
import com.booknow.repositories.UserRepository;

/**
 * Author : Lasya Bentula
 * @Last Modified On 11/27/2020
 * Serive for book related operations
 */

@Transactional
@Service
public class BookNowService {

	private final static Logger LOGGER = Logger.getLogger(BookNowService.class.getName());
	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookCopyRepository bookCopyRepository;

	@Autowired
	BookHistoryRepository bookHistoryRepository;

	@Autowired
	BookLimitRepository bookLimitRepository;

	@Autowired
	FinePolicyRepository finePolicyRepository;
	
	@Autowired
	UserRepository userRepository;

	/**
	 * Get all book in db
	 * @return List<Book>
	 */
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	/**
	 * Search book based on search term. Matches author name, book title and isbn.
	 * @param searchString
	 * @return List<Book>
	 */
	public List<Book> searchBooks(String searchString) {
		return bookRepository.findAllBooks(searchString);// complete the query in bookRepository
	}
	
	/**
	 * Find all the issued books.
	 * @return List<BookHistory>
	 */
	public List<BookHistory> getAllIssuedBooks() {
		return bookHistoryRepository.findAllByStatus(Status.ISSUED);
	}

	/**
	 * Find all the returned books.
	 * @return List<BookHistory>
	 */
	public List<BookHistory> getReturnBooks() {
		return bookHistoryRepository.findAllByStatus(Status.RETURNED);
	}

	/**
	 * Issue a book to user.
	 * @param username
	 * @param isbn of the book
	 * @return List<BookHistory>
	 */
	public void issueBook(String username, String isbn) {
		
		//check if book can be be issued
		Long issued = bookCopyRepository.getIssuedCopyCountByUsernameAndStatus(username);
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("Invalid username");
		}
		if(user.getUserStatus().equals(Enums.UserStatus.INACTIVE)) {
			throw new RuntimeException("User is inactive");
		}
		int limit = 0;
				//bookLimitRepository.findTotalLimitByUserType(user.getUserType());
		if(user.getUserType().equals(Enums.UserType.STUDENT)) {
			 limit = 3;
		} else if(user.getUserType().equals(Enums.UserType.FACULTY)) {
			 limit = 5;
		}
		if(issued.intValue() >= limit) {
			throw new RuntimeException("Reached Limit");
		}
		
		//Get the book based on the isbn number
		Book book = bookRepository.findBookByIsbn(isbn);
		Long bookId = book.getId();
		List<BookCopy> availableCopies = bookCopyRepository.getAllAvailableCopiesByBookId(bookId);

		//Check if any copy is avaliable
		if (availableCopies.isEmpty()) {
			return;
		}

		// Set the first avaliable copy to the user
		BookCopy bookCopy = availableCopies.get(0);
		bookCopy.setBorrower(username);
		bookCopy.setStatus(Enums.Status.ISSUED);
		bookCopy = bookCopyRepository.save(bookCopy);
		LOGGER.info("Book issued to " + username + "successfully");
		// add an entry in book history
		// assume user is student and he can borrow for 20 days
		// due date is now()+20 days
		int returnInDays = bookLimitRepository.findTotalLimitByUserType(user.getUserType());
		
		Date dueDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dueDate);
		c.add(Calendar.DATE, returnInDays);
		dueDate = c.getTime();

		//Set the book history
		BookHistory history = new BookHistory();
		history.setBorrower(username);
		history.setAction_by(username);// get logged in username
		history.setAction_date(new Date());
		history.setBookCopyId(bookCopy.getId());
		history.setAuthor(book.getAuthor());
		history.setBookId(bookId);
		history.setFine(0.00);
		history.setName(book.getName());
		history.setIsbn(book.getIsbn());
		history.setDue_date(dueDate);
		history.setStatus(Enums.Status.ISSUED);
		bookHistoryRepository.save(history);
	}

	/**
	 * Calculate the fine amount for a book.
	 * @param username
	 * @param isbn of the book
	 * @return List<BookHistory>
	 */
	public Double calculateFineAmount(String username, String isbn) {
		Book book = bookRepository.findBookByIsbn(isbn);
		Long bookId = book.getId();
		BookCopy bookCopy = bookCopyRepository.getIssuedCopyByUsernameAndBookId(username, bookId);
		BookHistory oldHistory = bookHistoryRepository.findByBookCopyId(bookCopy.getId());
		Double fine = Double.valueOf(0);

		Date dueDate = oldHistory.getDue_date();
		if (dueDate.before(new Date())) {
			// due date passed
			// Calculate fine
			// assuming fine for one day for student is $10
			Double finePerDay = Double.valueOf(finePolicyRepository.findFinePerDayByUserType(Enums.UserType.STUDENT));
			int diffInDays = (int) ((new Date().getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24));
			fine = diffInDays * finePerDay;
		}
		return Math.max(fine, 0);
	}

	/**
	 * Return a issued book.
	 * @param username
	 * @param isbn of the book
	 * @return List<BookHistory>
	 */
	public void returnBook(String username, String isbn) {

		Book book = bookRepository.findBookByIsbn(isbn);
		Long bookId = book.getId();
		BookCopy bookCopy = bookCopyRepository.getIssuedCopyByUsernameAndBookId(username, bookId);

		if (bookCopy == null) {
			throw new RuntimeException("Book with name " + book.getName() + "was not issued to " + username);
		}

		bookCopy.setBorrower(null);
		bookCopy.setStatus(Enums.Status.AVAILABLE);
		bookCopyRepository.save(bookCopy);
		LOGGER.info("Marked Book as Returned by " + username + "successfully");

		// Calculate fine
		Double fine = calculateFineAmount(username, isbn);

		BookHistory oldHistory = bookHistoryRepository.findByBookCopyId(bookCopy.getId());
		/*BookHistory history = new BookHistory();
		history.setBorrower(username);
		history.setAction_by(username);// get logged in username
		history.setAction_date(new Date());
		history.setBookCopyId(bookCopy.getId());
		history.setAuthor(book.getAuthor());
		history.setBookId(bookId);
		history.setFine(fine);
		history.setName(book.getName());
		history.setIsbn(book.getIsbn());
		history.setDue_date(oldHistory.getDue_date());
		*/
		oldHistory.setReturnDate(new Date());
		oldHistory.setAction_date(new Date());
		oldHistory.setFine(fine);
		oldHistory.setStatus(Enums.Status.RETURNED);
		bookHistoryRepository.save(oldHistory);
	}

	/**
	 * Add book to db.
	 * @param ManageBookInput set of inputs from user
	 */
	public void addBook(ManageBookInput manageBookInput) {
		Book book = new Book();
		book.setAuthor(manageBookInput.getAuthor());
		book.setName(manageBookInput.getName());
		book.setIsbn(manageBookInput.getIsbn());
		List<BookCopy> bookCopies = new ArrayList<>();
		for (int i = 0; i < manageBookInput.getCopies(); i++) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(book);
			bookCopy.setStatus(Status.AVAILABLE);
			bookCopies.add(bookCopy);
		}
		book.setCopies(bookCopies);
		bookRepository.save(book);
		bookCopyRepository.saveAll(bookCopies);
	}
	
	/**
	 * Edit a book already in db.
	 * @param ManageBookInput set of inputs from user
	 */
	public void editBook(ManageBookInput manageBookInput) {
		Book book = bookRepository.findBookByIsbn(manageBookInput.getIsbn());

		List<BookCopy> bookCopies = new ArrayList<>();
		for (int i = 0; i < manageBookInput.getCopies(); i++) {
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(book);
			bookCopy.setStatus(Status.AVAILABLE);
			bookCopies.add(bookCopy);
		}

		book.setAuthor(manageBookInput.getAuthor());
		book.setName(manageBookInput.getName());

		book.setCopies(bookCopies);

		bookCopyRepository.deleteByBookId(book.getId());
		bookCopyRepository.saveAll(bookCopies);

		bookRepository.save(book);

	}
}