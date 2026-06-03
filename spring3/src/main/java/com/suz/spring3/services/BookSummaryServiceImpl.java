package com.suz.spring3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suz.spring3.models.Book;
import com.suz.spring3.repo.BookRepository;

@Service
public class BookSummaryServiceImpl implements BookSummaryService {
	
	private final BookRepository repo;
	
	@Autowired
	public BookSummaryServiceImpl(BookRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public List<Book> getAllBooks() {
		return this.repo.findAll();
	}
}
