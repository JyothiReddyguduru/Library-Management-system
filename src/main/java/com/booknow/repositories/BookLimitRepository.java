package com.booknow.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booknow.entities.BookLimitRule;
import com.booknow.entities.Enums;

/**
 * 
 * @author Lasya
 *
 */
@Repository
@Transactional
public interface BookLimitRepository  extends JpaRepository<BookLimitRule, Long> {
	
	@Query("select l.totalLimit from BookLimitRule l where l.userType =?1")
	int findTotalLimitByUserType(Enums.UserType userType);

}
