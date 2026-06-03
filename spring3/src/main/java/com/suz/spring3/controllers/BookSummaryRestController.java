package com.suz.spring3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suz.spring3.models.Book;
import com.suz.spring3.services.BookSummaryService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping(path = "/books")
public class BookSummaryRestController {
	
	private BookSummaryService bSvc;
	
	@Autowired
	public BookSummaryRestController(BookSummaryService bSvc) {
		this.bSvc = bSvc;
	}
	
	
	@RequestMapping(
			produces = "application/json",
			method = RequestMethod.GET
			)
	public @ResponseBody List<Book> getAll() {
		return this.bSvc.getAllBooks();
	}

}
