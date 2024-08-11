package com.teamCollaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.dto.ProjectDTO;
import com.teamCollaboration.dto.TeamDTO;
import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.Team;
import com.teamCollaboration.services.ManagerService;

@RestController
@CrossOrigin
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
	    public List<ProjectDTO> getAllProjects() {
	        return managerService.getAllProjects();
	    }

	    // Endpoint to create a new project
	    @PostMapping("/projects")
	    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
	    public ProjectDTO createProject(@RequestBody ProjectDTO project) {
	        return managerService.createProject(project);
	    }

	    // Endpoint to create a new team
	    @PostMapping("/teams")
	    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
	    public TeamDTO createTeam(@RequestBody TeamDTO team) {
	        return managerService.createTeam(team);
	    }
	    
	    @GetMapping("/teams")
	    @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
	    public List<TeamDTO> getTeams() {
	        return managerService.getTeamsList();
	    }

	    // Endpoint to add members to a team
	    @PostMapping("/teams/{teamId}/members")
	    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	    public String addTeamMembers(@PathVariable Long teamId, @RequestBody List<Long> memberIds) {
	    	managerService.addTeamMembers(teamId, memberIds);
	        return "Done";
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
