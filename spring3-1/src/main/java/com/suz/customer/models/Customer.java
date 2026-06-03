package com.suz.customer.models;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	//customer_id	name	date_of_birth	nationality	email_address	address
	
	// Data
	/*
	 * Example data that should be served by your REST APIs:

customer_id	name	date_of_birth	nationality	email_address	address
1	John Doe	1990-09-15	SAUDI	user1@leantech.me	"1 National Arabic Towers Riyadh Saudi Arabia"
2	Jane Bloggs	1987-09-22	BRITISH	user2@leantech.me	"140 Tabernacle Street Shoreditch EC2A 4SD London UK"
3	Mike Smith	1995-02-07	AMERICAN	user3@leantech.me	"20045 Ocean Drive Los Angeles CA USA"
	 */
	
	public Customer() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int customerId;
	
	String name;
	
	LocalDate dateOfBirth;
	
	String nationality;
	
	String emailAddress;
	
	String address;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
