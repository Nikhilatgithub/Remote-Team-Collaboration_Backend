package com.teamCollaboration.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.custom_exceptions.TeamCollaborationException;
import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Role;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.repository.EmployeeRepository;
import com.teamCollaboration.repository.ProjectRepository;
import com.teamCollaboration.repository.RoleRepository;
import com.teamCollaboration.repository.TaskRepository;

@Service
public class AdminService {

    private  ProjectRepository projectRepository;
    private  EmployeeRepository userRepository;
    private  TaskRepository taskRepository;
    private  RoleRepository roleRepository;

    @Autowired
    public AdminService(RoleRepository roleRepository,ProjectRepository projectRepository, EmployeeRepository userRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
    }

    public Project createProject(Project project) {
        // Add business logic if needed
        return projectRepository.save(project);
    }
    
    public List<Project> projectList() {
        // Add business logic if needed
        return projectRepository.findAll();
    }

    public Project updateProject(Long projectId, Project updatedProject) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new TeamCollaborationException("Project not found"));
        // Update project logic
        return projectRepository.save(updatedProject);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public Employee addUser(Employee user) {
        // Add business logic if needed
        return userRepository.save(user);
    }

    public Employee updateUser(Long userId, Employee updatedUser) {
    	Employee user = userRepository.findById(userId)
                .orElseThrow(() -> new TeamCollaborationException("User not found"));
        // Update user logic
        return userRepository.save(updatedUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public Task addTask(Task task) {
        // Add business logic if needed
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Long taskId, Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TeamCollaborationException("Task not found"));
        // Update task logic
        return taskRepository.save(updatedTask);
    }

    public Employee assignRoleToUser(Long userId, Long roleId) {
        // Implement logic to assign role to user
    	Employee user = userRepository.findById(userId)
                .orElseThrow(() -> new TeamCollaborationException("User not found"));
        // Assign role to user logic
    	Role role = roleRepository.getById(roleId);
    	user.setRole(role);
        return userRepository.save(user);
    }

    public Employee updateUserRole(Long userId, Long roleId) {
        // Implement logic to update user's role
    	Employee user = userRepository.findById(userId)
                .orElseThrow(() -> new TeamCollaborationException("User not found"));
        // Update user's role logic
    	Role role = roleRepository.getById(roleId);
    	user.setRole(role);
        return userRepository.save(user);
    }
}
