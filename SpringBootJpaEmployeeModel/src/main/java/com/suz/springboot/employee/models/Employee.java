/**
 * 
 */
package com.suz.springboot.employee.models;


import java.beans.JavaBean;

import org.springframework.data.repository.NoRepositoryBean;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * By default, the Entity class name should be 
 */
@Entity
@Table(name = "Employees", catalog = "learners")

// Use of this?
//@NoRepositoryBean

// Perhaps, redundant?
@JavaBean
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// Blank final does work!
	private final String id;
	
	private int age;
	private String name;
	
	// ERROR if the column does not exist in the table (psql)
//	@Nullable
//	private String location;
	
	
	// No arg constructor required, else 
	/**
	 * Exception:
		org.hibernate.InstantiationException: No default constructor for entity 'com.suz.springboot.employee.models.Employee'
		at org.hibernate.metamodel.internal.EntityInstantiatorPojoStandard.instantiate(EntityInstantiatorPojoStandard.java:93) ~[hibernate-core-6.5.3.Final.jar:6.5.3.Final]

	 */
	public Employee() {
		id = "aa";
	}
	
	public Employee(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}

	public String getId() {
		return id;
	}
}
