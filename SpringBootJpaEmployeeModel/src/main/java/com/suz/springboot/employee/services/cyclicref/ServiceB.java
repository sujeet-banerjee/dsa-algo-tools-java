package com.suz.springboot.employee.services.cyclicref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suz.springboot.employee.services.EmployeeService;

@Service
public class ServiceB {
	
	// This cycle is detected only during the boot up time (runtime), 
	// causing "App Failed to Start"
	// But no problem with the compilation.
	
	//@Autowired
	//EmployeeService sA;
	
	@Autowired
	public ServiceB(ServiceA sA) {
		// TODO Auto-generated constructor stub
	}
	
}
