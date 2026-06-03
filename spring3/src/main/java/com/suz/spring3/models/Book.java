package com.suz.spring3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "learners", name = "TEST")

public class Book {
	@Id
	@Column(name = "col1")
	private String name;
	@Column(name = "col2")
	private int pages;
	
	public Book() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	
}
