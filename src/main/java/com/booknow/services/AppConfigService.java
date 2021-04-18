package com.booknow.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.booknow.entities.FineRule;
import com.booknow.entities.User;
import com.booknow.entities.BookLimitRule;
import com.booknow.entities.Enums.UserStatus;
import com.booknow.entities.Enums.UserType;
import com.booknow.repositories.FinePolicyRepository;
import com.booknow.repositories.UserRepository;
import com.booknow.repositories.BookLimitRepository;
/**
 * 
 * @author Jyothi and Lasya
 * @Last Modified On 11/27/2020
 *
 */
@Service
public class AppConfigService {
	
	//private final static Logger LOGGER = Logger.getLogger(AppConfigService.class.getName());

	@Autowired
	private BookLimitRepository bookLimitRepository;
	
	@Autowired
	private FinePolicyRepository finePolicyRepository;	
	
	@Autowired
	private UserRepository userRepository;

	//fetch book limit
	public List<BookLimitRule> getBookLimitPolicy() {
		return bookLimitRepository.findAll();
	}

	//fetch fine rules
	public List<FineRule> getFinePolicy() {
		return finePolicyRepository.findAll();
	}
	
	
	/**
	 * 
	 * function to set up master data for fine policy
	 */
	public void setupFinePolicy() {
		
		//save rules for student usertype
		FineRule fineRule = new FineRule();
		fineRule.setName("1sday");
		fineRule.setLabel("1 Day");
		fineRule.setUserType(UserType.STUDENT);
		fineRule.setFinePerDay(5.00);
		finePolicyRepository.save(fineRule);
		
		fineRule = new FineRule();
		fineRule.setName("1<sday<5");
		fineRule.setLabel("1 < Day < 5");
		fineRule.setUserType(UserType.STUDENT);
		fineRule.setFinePerDay(10.00);
		finePolicyRepository.save(fineRule);
		
		fineRule = new FineRule();
		fineRule.setName("5<=sday<10");
		fineRule.setLabel("5 <= Day < 10");
		fineRule.setUserType(UserType.STUDENT);
		fineRule.setFinePerDay(15.00);
		finePolicyRepository.save(fineRule);
		
		fineRule = new FineRule();
		fineRule.setName("sday>=10");
		fineRule.setLabel("Day >= 10");
		fineRule.setUserType(UserType.STUDENT);
		fineRule.setFinePerDay(5.00);
		finePolicyRepository.save(fineRule);
		
		//rules for faculty usertype
		fineRule = new FineRule();
		fineRule.setName("1fday");
		fineRule.setLabel("1 Day");
		fineRule.setUserType(UserType.FACULTY);
		fineRule.setFinePerDay(5.00);
		finePolicyRepository.save(fineRule);
		
		fineRule = new FineRule();
		fineRule.setName("1<fday<5");
		fineRule.setLabel("1 < Day < 5");
		fineRule.setUserType(UserType.FACULTY);
		fineRule.setFinePerDay(10.00);
		finePolicyRepository.save(fineRule);
		
		fineRule = new FineRule();
		fineRule.setName("5<=fday<10");
		fineRule.setLabel("5 <= Day < 10");
		fineRule.setUserType(UserType.FACULTY);
		fineRule.setFinePerDay(15.00);
		finePolicyRepository.save(fineRule);
		
		fineRule = new FineRule();
		fineRule.setName("fday>=10");
		fineRule.setLabel("Day >= 10");
		fineRule.setUserType(UserType.FACULTY);
		fineRule.setFinePerDay(5.00);
		finePolicyRepository.save(fineRule);

		
	}
	
	 /* function to set up master data for fine policy*/
	public void setupBookLimitPolicy() {
		
		BookLimitRule bookLimitRule = new BookLimitRule();
		bookLimitRule.setName("studentPolicy");
		bookLimitRule.setUserType(UserType.STUDENT);
		bookLimitRule.setTotalLimit(10);
		
		bookLimitRepository.save(bookLimitRule);
		
		bookLimitRule = new BookLimitRule();
		bookLimitRule.setName("facultyPolicy");
		bookLimitRule.setUserType(UserType.FACULTY);
		bookLimitRule.setTotalLimit(20);
		
		bookLimitRepository.save(bookLimitRule);
		
	}
	
	
	/**
	 * utility method to setup admin and librarian
	 */
	public void setupUsers() {
		User userObject = new User();
		userObject.setEmailAddress("gudurujyothireddy@gmail.com");
		userObject.setFullName("Jyothi Reddy Guduru");
		userObject.setRoles("ADMIN");
		userObject.setUserType(UserType.ADMIN);
		//set the user type
		//encrypt password before stroing in the database
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode("pass");
		userObject.setPassword(password);
		userObject.setUserStatus(UserStatus.ACTIVE);
		userRepository.save(userObject);
		
		
		userObject = new User();
		userObject.setEmailAddress("gudurujyothireddy@gmail.com");
		userObject.setFullName("Jyothi Reddy Guduru");
		userObject.setRoles("ADMIN");
		//set the user type
		userObject.setUserType(UserType.LIBRARIAN);
		//encrypt password before stroing in the database
		userObject.setPassword(password);
		userObject.setUserStatus(UserStatus.ACTIVE);
		userRepository.save(userObject);

		
	}

	public void editBookLimitRule(BookLimitRule bookLimitRule) {
		if(bookLimitRule.getId() != null) {
			bookLimitRepository.save(bookLimitRule);		
		} else {
			throw new RuntimeException("New rules cannot be added");
		}
	}

	public void editFineRule(FineRule fineRule) {
		if(fineRule.getId() != null) {
			finePolicyRepository.save(fineRule);
		} else {
			throw new RuntimeException("New fine rules cannot be added");
		}
	}

}
