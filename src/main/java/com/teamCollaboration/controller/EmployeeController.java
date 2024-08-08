package com.teamCollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.services.EmployeeService;



@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Endpoint to view projects assigned to the employee
    @GetMapping("/projects")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Project> viewEmployeeProjects() {
        return employeeService.getEmployeeProjects();
    }

    // Endpoint to view team members of the employee
    @GetMapping("/team-members")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<User> viewTeamMembers() {
        return employeeService.getTeamMembers();
    }

    // Endpoint to view tasks assigned to the employee
    @GetMapping("/tasks")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public List<Task> viewAssignedTasks() {
        return employeeService.getAssignedTasks();
    }

    // Endpoint to update task status
    @PutMapping("/tasks/{taskId}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public Task updateTaskStatus(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        return employeeService.updateTaskStatus(taskId, updatedTask.getStatus());
    }

    // Endpoint to update employee's own profile
    @PutMapping("/profile")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public User updateProfile(@RequestBody User updatedUser) {
        return employeeService.updateProfile(updatedUser);
    }	
}
