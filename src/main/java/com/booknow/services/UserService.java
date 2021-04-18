package com.booknow.services;

import java.util.Base64;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.booknow.entities.BookHistory;
import com.booknow.entities.Enums;
import com.booknow.entities.Enums.UserStatus;
import com.booknow.entities.Enums.UserType;
import com.booknow.entities.User;
import com.booknow.model.TestModel;
import com.booknow.repositories.BookHistoryRepository;
import com.booknow.repositories.UserRepository;

/**
 * Service for user operations
 * 
 * @author Jyothi Reddy Guduru
 * @LastModifiedOn 11/21/2020
 *
 */
@Service
public class UserService {

	private final static Logger LOGGER = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookHistoryRepository histRepo;

	/**
	 * Method to decode password from angular and fetch password from db and
	 * compare to see if user is valid
	 * 
	 * @return User
	 */
	public User getLoggedInUser(TestModel user) {

		User dbuser = userRepository.findByUsername(user.getUsername());
		if (dbuser == null) {
			throw new RuntimeException("Invalid username");
		}
		if(dbuser.getUserStatus().equals(Enums.UserStatus.INACTIVE)) {
			throw new RuntimeException("User is inactive");
		}
		// System.out.println("================");
		// return getLoggedInUserAsOptional().orElseThrow(() -> new
		// RuntimeException("Current session is anonymous or not an instance of
		// Principal"));
		System.out.println("User password: " + user.getPassword());
		byte[] userpass = Base64.getDecoder().decode(user.getPassword());
		byte[] dbpass = Base64.getDecoder().decode(dbuser.getPassword());
		// String decodeString1= new String(userpass);
		// System.out.println("User password: " + new String(userpass));
		// System.out.println("db password: " + new String(dbpass));
		// System.out.println("================");

		if (new String(dbpass).equals(new String(userpass))) {
			return dbuser;
		} else {
			throw new RuntimeException("Invalid Credentials");
		}
		// return userRepository.findByUsernameAndPassword(user.getUsername(),
		// user.getPassword());
	}

	public Optional<User> getLoggedInUserAsOptional() {
		return Optional.ofNullable(SecurityContextHolder.getContext()).map(e -> e.getAuthentication())
				.filter(User.class::isInstance).map(User.class::cast);
	}

	/**
	 * Method to fetch all the users in the system
	 * 
	 * @return
	 */
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Function to add a user into system Can only add Student or faculty
	 * 
	 * @param user
	 * @return user
	 */
	public User addUser(User user) {
		if (user.getUsername() == null || user.getFullName() == null || user.getEmailAddress() == null) {
			throw new RuntimeException("Please enter all details");
		}
		// validate username
		User newUser = userRepository.findByUsername(user.getUsername());
		if (newUser == null) {// new user
			// validate email address
			newUser = userRepository.findByEmailAddress(user.getEmailAddress());
			if (newUser == null) {// new user
				User userObject = new User();
				userObject.setEmailAddress(user.getEmailAddress());
				userObject.setFullName(user.getFullName());
				userObject.setRoles("USER");
				// set the user type
				if (user.getUserType().toString() == "STUDENT" || user.getUserType().toString() == "Student") {
					userObject.setUserType(UserType.STUDENT);
				} else if (user.getUserType().toString() == "FACULTY" || user.getUserType().toString() == "Faculty") {
					userObject.setUserType(UserType.FACULTY);
				} else if (user.getUserType().toString() == "LIBRARIAN"
						|| user.getUserType().toString() == "Librarian") {
					userObject.setUserType(UserType.LIBRARIAN);
				} else {
					throw new RuntimeException("Invalid user type");
				}
				// encrypt password before stroing in the database
				String originalInput = user.getPassword();
				String password = Base64.getEncoder().encodeToString(originalInput.getBytes());

				// BCryptPasswordEncoder passwordEncoder = new
				// BCryptPasswordEncoder();
				// String password = passwordEncoder.encode(user.getPassword());
				userObject.setPassword(password);
				userObject.setUserStatus(UserStatus.ACTIVE);

				// adding user into the database
				user = userRepository.save(userObject);
				LOGGER.info("User added successfully! - " + user.getUsername());
			} else {
				LOGGER.info("Duplicate email address found for " + user.getUsername());
				throw new RuntimeException("Duplicate Email Address");

			}
		} else {
			LOGGER.info("Duplicate Username found for " + user.getUsername());
			throw new RuntimeException("Duplicate Username");
		}
		return user;

	}

	/**
	 * method to fetch book history of a user based on username
	 * 
	 * @param name
	 * @return
	 */
	public List<BookHistory> getMyHistory(String name) {
		return histRepo.findAllByBorrower(name);
	}

	/**
	 * method to fetch all users book borrowing history
	 */
	public List<BookHistory> getHistory() {
		return histRepo.findAll();
	}
}
