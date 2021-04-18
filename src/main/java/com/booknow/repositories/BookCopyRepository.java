package com.booknow.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booknow.entities.BookCopy;

/**
 * Author : Lasya Bentula
 */

@Repository
@Transactional
public interface BookCopyRepository  extends JpaRepository<BookCopy, Long> {

	//get all book copies that are e.issued to any user
	@Query("select e from BookCopy e where e.borrower is NOT NULL")
	List<BookCopy> getAllIssuedCopies();
	
	//get all book copies that are issued to given user

	@Query("select e from BookCopy e where e.borrower=?1 and e.book.id=?2")
	BookCopy getIssuedCopyByUsernameAndBookId(String username, Long bookId);
	
	@Query("select e from BookCopy e where e.book.id=?1 and e.status='AVAILABLE'")
	List<BookCopy> getAllAvailableCopiesByBookId(Long bookId);
	
	Long deleteByBookId(Long bookId);
	
	//SELECT COUNT(*) FROM `book_copies` WHERE borrower='Jyothi' AND STATUS='ISSUED';
	@Query("select count(e) from BookCopy e where e.borrower=?1 and e.status='ISSUED'")
	Long getIssuedCopyCountByUsernameAndStatus(String username);
	
}
