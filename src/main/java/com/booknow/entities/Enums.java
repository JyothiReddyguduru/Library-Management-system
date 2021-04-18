package com.booknow.entities;

/**
 * all enums
 * @author Jyothi
 *
 */
public class Enums {
	
	public enum Status {
		AVAILABLE, ISSUED,RETURNED
	}
	
	public enum Action {
		ISSUED, RETURNED
	}
	
	public enum UserType {
		LIBRARIAN, STUDENT, FACULTY, ADMIN
	}
	public enum UserStatus{
		ACTIVE, INACTIVE
	}

}
