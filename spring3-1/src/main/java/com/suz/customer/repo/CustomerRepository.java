package com.suz.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suz.customer.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
