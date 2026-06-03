package com.suz.customer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suz.customer.models.Customer;
import com.suz.customer.services.CustomerService;

@RestController
@RequestMapping(path = "/customers")
public class CustomerDetailsRestController {
	
	private final CustomerService svc;
	
	public CustomerDetailsRestController(CustomerService svc) {
		this.svc = svc;
	}
	
	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable int id) {
		return this.svc.getCustomerById(id);
	}
}
