package com.teamCollaboration.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.dto.EmployeeDTO;
import com.teamCollaboration.dto.ProjectDTO;
import com.teamCollaboration.dto.TaskDTO;
import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.Team;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.services.AdminService;
import com.teamCollaboration.services.TeamService;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private  AdminService adminService;
	@Autowired
    private  PasswordEncoder passwordEncoder;
	@Autowired
	private  TeamService teamService;
//    public AdminController(AdminService adminService) {
//        this.adminService = adminService;
//    }

    // Endpoint to create a new project
    @PostMapping("/projects")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public Project createProject(@RequestBody Project project) {
        return adminService.createProject(project);
    }
    
    @GetMapping("/projects")
     @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
     public List<ProjectDTO> getAllProjects() {
         return adminService.projectList();
     }

    // Endpoint to update an existing project
    @PutMapping("/projects/{projectId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ProjectDTO updateProject(@PathVariable Long projectId, @RequestBody ProjectDTO project) {
        return adminService.updateProject(projectId, project);
    }

    // Endpoint to delete a project
    @DeleteMapping("/projects/{projectId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public void deleteProject(@PathVariable Long projectId) {
        adminService.deleteProject(projectId);
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public List<EmployeeDTO> getAllUsers() {
        return adminService.usersList();
    }

    // Endpoint to add a new user
    @PostMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public EmployeeDTO addUser(@RequestBody EmployeeDTO user) {
    	 user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return adminService.addUser(user);
    }

    // Endpoint to update an existing user
    @PutMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public EmployeeDTO updateUser(@PathVariable Long userId, @RequestBody EmployeeDTO user) {
    	//user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return adminService.updateUser(userId, user);
    }

    // Endpoint to delete a user
    @DeleteMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public void deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
    }

    // Endpoint to add a new task
    @PostMapping("/tasks")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public TaskDTO addTask(@RequestBody TaskDTO task) {
        return adminService.addTask(task);
    }
    
    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public List<TaskDTO> getTask() {
        return adminService.taskList();
    }

    // Endpoint to delete a task
    @DeleteMapping("/tasks/{taskId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public void deleteTask(@PathVariable Long taskId) {
        adminService.deleteTask(taskId);
    }

    // Endpoint to update a task
    @PutMapping("/tasks/{taskId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public TaskDTO updateTask(@PathVariable Long taskId, @RequestBody TaskDTO task) {
        return adminService.updateTask(taskId, task);
    }

    // Endpoint to assign role to user
    @PostMapping("/users/{userId}/assign-role/{roleId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        return adminService.assignRoleToUser(userId, roleId);
    }

    // Endpoint to update user's role
    @PutMapping("/users/{userId}/update-role/{roleId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        return adminService.updateUserRole(userId, roleId);
    }
    
    
    @GetMapping("/teams")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
    
    @PutMapping("teams/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        Optional<Team> existingTeam = teamService.getTeamById(id);
        if (existingTeam.isPresent()) {
            Team team = existingTeam.get();
            // Update fields based on updatedTeam
            team.setName(updatedTeam.getName());
            team.setDescription(updatedTeam.getDescription());

            // Save updated team entity
            Team savedTeam = teamService.saveOrUpdateTeam(team);
            return ResponseEntity.ok(savedTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
