package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	 @Autowired
	 private EmployeeRepository employeeRepository;

	   
//	    public EmployeeService(EmployeeRepository employeeRepository) {
//	        this.employeeRepository = employeeRepository;
//	    }

	    // Method to save or update an employee
	    public Employee saveOrUpdateEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	  

	    // Method to find employee by ID
	    public Optional<Employee> getEmployeeById(Long id) {
	        return employeeRepository.findById(id);
	    }

	    // Method to delete employee by ID
	    public void deleteEmployeeById(Long id) {
	        employeeRepository.deleteById(id);
	    }
}
