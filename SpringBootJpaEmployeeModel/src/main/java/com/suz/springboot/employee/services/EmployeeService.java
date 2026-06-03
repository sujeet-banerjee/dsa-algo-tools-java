package com.suz.springboot.employee.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suz.springboot.employee.models.Employee;


/**
 * The @Service annotation has no effect on the interface
 * It's the impl which needs to declare it!
 */
//@Service
public interface EmployeeService {

	List<Employee> getByAge(int age);

	List<Employee> getAll();

	Employee createEmployee(Employee emp);

}
