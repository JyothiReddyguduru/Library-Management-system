package com.booknow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booknow.entities.Enums;
import com.booknow.entities.FineRule;
/**
 * 
 * @author Jyothi
 * @Last Modified On 11/27/2020
 *
 */
@Repository
public interface FinePolicyRepository extends JpaRepository<FineRule, Long> {

	@Query("select l.finePerDay from FineRule l where l.userType =?1")
	int findFinePerDayByUserType(Enums.UserType userType);
}

