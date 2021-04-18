package com.booknow.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booknow.entities.Book;

/**
 * 
 * @author Lasya
 * @Last Modified On 11/27/2020
 *
 */
@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
		
	@Query("select e from Book e where e.name LIKE ?1 or e.isbn LIKE ?1 or e.author LIKE ?1")
	List<Book> findAllBooks(String searchStr);
	
	@Query("select b from Book b where b.isbn LIKE ?1")
	Book findBookByIsbn(String isbn);

}
