package com.booknow.repositories;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booknow.entities.BookHistory;
import com.booknow.entities.Enums;
import com.booknow.entities.Enums.Status;

/**
 * Author : Lasya Bentula
 * @Last Modified On 11/27/2020
 */

@Repository
@Transactional
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {

	// @Query("select e from bookHistory e where e.bookCopyId=?1 and e.status=?2")
	List<BookHistory> findAllByBookCopyIdAndStatus(Long bookCopyId, Enums.Status status);

	// @Query("select e from bookHistory e where e.bookCopyId=?1 and e.borrower=?2")
	List<BookHistory> findAllByBorrowerAndBookCopyId(String borrower, Long bookCopyId);

	List<BookHistory> findAll();
	
    List<BookHistory> findAllByStatus(Status status);
    
    BookHistory findByBookCopyId(Long bookCopyId);

	List<BookHistory> findAllByBorrower(String name);
}
