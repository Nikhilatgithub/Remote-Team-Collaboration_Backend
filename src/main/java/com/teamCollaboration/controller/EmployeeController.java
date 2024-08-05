package com.teamCollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.services.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService empService;
	
	@Autowired
	public EmployeeController(EmployeeService empService) {
		this.empService=empService;
	}
	
	@PostMapping()
	public String createNewEmployee(@RequestBody Employee emp) {
		empService.saveOrUpdateEmployee(emp);
		
		return "done";
	}
	
	@GetMapping()
	public List<Employee> getMethodName() {
		return empService.getAllEmployees();
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		empService.deleteEmployeeById(id);
		
		return "deleted";
	}
	
	
}
