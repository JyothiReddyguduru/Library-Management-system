package com.booknow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booknow.entities.Enums.UserStatus;
import com.booknow.entities.Enums.UserType;

/**
 * @author Lasya Bentula
 * created on - 11/1/2020
 * Last  modified on  - 11/21/2020
 * Entiry to hold user objects
 * */

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String fullName;

	@Column(unique=true)
	private String emailAddress;

	@Enumerated(EnumType.STRING)
	private UserType userType; 
	
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus; 
	
	private String roles;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		System.out.println("\n\nst"+password);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public UserType getUserType() {
		return userType;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	
}


