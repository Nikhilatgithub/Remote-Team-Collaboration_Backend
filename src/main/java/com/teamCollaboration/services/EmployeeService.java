package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.repository.ProjectRepository;
import com.teamCollaboration.repository.TaskRepository;
import com.teamCollaboration.repository.UserRepository;

@Service
public class EmployeeService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public EmployeeService(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    // Get currently logged in user (employee)
    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.getUserByEmail(email);
        return user;
    }

    public List<Project> getEmployeeProjects() {
        User employee = getCurrentUser();
        // Implement logic to fetch projects assigned to the employee
        // Example: return projectRepository.findByTeamMembersContaining(employee);
        return null;
    }

    public List<User> getTeamMembers() {
        User employee = getCurrentUser();
        // Implement logic to fetch team members of the employee
        // Example: return userRepository.findByTeam(employee.getTeam());
        return null;
    }

    public List<Task> getAssignedTasks() {
        User employee = getCurrentUser();
        // Implement logic to fetch tasks assigned to the employee
        // Example: return taskRepository.findByAssignedTo(employee);
        return null;
    }

    public Task updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        // Add more update logic if needed
        return taskRepository.save(task);
    }

    public User updateProfile(User updatedUser) {
        User currentUser = getCurrentUser();
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPassword(updatedUser.getPassword()); // Add encryption logic if needed
        // Add more update logic if needed
        return userRepository.save(currentUser);
    }
}