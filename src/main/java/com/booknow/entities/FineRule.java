package com.booknow.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.booknow.entities.Enums.UserType;

/***
 * 
 * @author Jyothi
 * @createdon 11/19
 * @LastModified on 11/19
 * Entity to manage fine policy. 
 * Policy stores the fine amount per day that is payable by student of staff if book is not returned before or on due date.
 */
@Entity
@Table(name="fine_rules")
public class FineRule {
	
	//auto-generated id column to identify a fine rule
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//unique column for rule name
	@Column(unique=true)
	private String name;
	
	@Column
	private String label;
	
	//column to hold fine amount per day
	@Column
	private Double finePerDay;
	
	//Column to hold the user type- it has two values - student and faculty
	@Column(unique=true)
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	public FineRule() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public UserType getUserType() {
		return userType;
	}

	public Double getFinePerDay() {
		return finePerDay;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFinePerDay(Double finePerDay) {
		this.finePerDay = finePerDay;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
