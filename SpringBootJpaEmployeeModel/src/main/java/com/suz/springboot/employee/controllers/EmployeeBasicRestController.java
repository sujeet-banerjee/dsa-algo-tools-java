package com.suz.springboot.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.suz.springboot.employee.models.Employee;
import com.suz.springboot.employee.services.EmployeeService;

@RestController()
@RequestMapping(path = "/example/employeemodel")
public class EmployeeBasicRestController {
	
	private EmployeeService svc;
	
	@Autowired
	public EmployeeBasicRestController(EmployeeService svc) {
		this.svc = svc;
	}
	
	@RequestMapping(
			produces = "application/json", 
			method = RequestMethod.GET, 
			path = "/employees")
	public List<Employee> getEmployees() {
		return this.svc.getAll();
	}
	
	@RequestMapping(consumes = "application/json",
			method = RequestMethod.POST,
			path = "/employees",
			produces = "application/json")
	public Employee createEmployee(Employee emp) {
		return this.svc.createEmployee(emp);
	}
	
}
