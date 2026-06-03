/**
 * 
 */
package com.suz.springboot.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suz.springboot.employee.models.Employee;

/**
 * 
 */
public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	List<Employee> findByAge(int age);
	
}
