package com.booknow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booknow.entities.User;
/**
 * @Last Modified On 11/27/2020
 * @author Jyothi
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String Username);

	User findByEmailAddress(String emailAddress);
	
	User findByUsernameAndPassword(String username, String password);

}
