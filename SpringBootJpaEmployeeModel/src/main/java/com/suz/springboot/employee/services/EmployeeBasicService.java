/**
 * 
 */
package com.suz.springboot.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suz.springboot.employee.models.Employee;
import com.suz.springboot.employee.repo.EmployeeRepository;
import com.suz.springboot.employee.services.cyclicref.ServiceA;

/**
 * If you skip the @Service on the impl, you get this error
 * <pre>
		Parameter 0 of constructor in 
			com.suz.springboot.employee.controllers.EmployeeBasicRestController 
		required a bean of type 
			'com.suz.springboot.employee.services.EmployeeService' 
		that could not be found.

 * </pre>
 * 
 */
@Service
public class EmployeeBasicService implements EmployeeService {

	// Composes a Repository for persistence
	private final EmployeeRepository empRepo;
	
	@Autowired
	public EmployeeBasicService(
			EmployeeRepository empRepo,
			ServiceA sA
			) {
		System.out.println("The service get a repo instance: "+ empRepo);
		this.empRepo = empRepo;
	}
	
	
	@Override
	public List<Employee> getAll(){
		return this.empRepo.findAll();
	}
	
	
	@Override
	public List<Employee> getByAge(int age) {
		return this.empRepo.findByAge(age);
	}


	@Override
	public Employee createEmployee(Employee emp) {
		return this.empRepo.save(emp);
	}
	
}
