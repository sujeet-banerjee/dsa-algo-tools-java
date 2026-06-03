package com.suz.customer.services;

import org.springframework.stereotype.Service;

import com.suz.customer.models.Customer;
import com.suz.customer.repo.CustomerRepository;

@Service
public class CustomerServiceDefaultImpl implements CustomerService {
	
	private final CustomerRepository repo;
	
	public CustomerServiceDefaultImpl(CustomerRepository repo) {
		this.repo = repo;
	}

	@Override
	public Customer getCustomerById(int id) {
		return this.repo.getReferenceById(id);
	}

}
