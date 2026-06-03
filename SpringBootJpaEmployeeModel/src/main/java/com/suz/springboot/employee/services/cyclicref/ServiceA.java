package com.suz.springboot.employee.services.cyclicref;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
	
	// Commented code to avoid cycle - reason given below.
	
//	@Autowired
//	ServiceB sB;
	
	
	// This cycle is detected only during the boot up time (runtime), 
	// causing "App Failed to Start"
	// But no problem with the compilation.
	
//	public ServiceA(ServiceB sB) {
//		// TODO Auto-generated constructor stub
//	}
	
}
