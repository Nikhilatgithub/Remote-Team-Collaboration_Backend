package com.teamCollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.Team;
import com.teamCollaboration.services.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	  private  ManagerService managerService;

	    @Autowired
	    public ManagerController(ManagerService managerService) {
	        this.managerService = managerService;
	    }

	    // Endpoint to view all projects
	    @GetMapping("/projects")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public List<Project> getAllProjects() {
	        return managerService.getAllProjects();
	    }

	    // Endpoint to create a new project
	    @PostMapping("/projects")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public Project createProject(@RequestBody Project project) {
	        return managerService.createProject(project);
	    }

	    // Endpoint to create a new team
	    @PostMapping("/teams")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public Team createTeam(@RequestBody Team team) {
	        return managerService.createTeam(team);
	    }

	    // Endpoint to add members to a team
	    @PostMapping("/teams/{teamId}/members")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public Team addTeamMembers(@PathVariable Long teamId, @RequestBody List<Long> memberIds) {
	        return managerService.addTeamMembers(teamId, memberIds);
	    }

	    // Endpoint to create a new task
	    @PostMapping("/tasks")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public Task createTask(@RequestBody Task task) {
	        return managerService.createTask(task);
	    }

	    // Endpoint to update a task
	    @PutMapping("/tasks/{taskId}")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task) {
	        return managerService.updateTask(taskId, task);
	    }
}
