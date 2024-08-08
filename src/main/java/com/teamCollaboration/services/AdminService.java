package com.teamCollaboration.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.repository.ProjectRepository;
import com.teamCollaboration.repository.TaskRepository;
import com.teamCollaboration.repository.UserRepository;

@Service
public class AdminService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public AdminService(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public Project createProject(Project project) {
        // Add business logic if needed
        return projectRepository.save(project);
    }

    public Project updateProject(Long projectId, Project updatedProject) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        // Update project logic
        return projectRepository.save(updatedProject);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public User addUser(User user) {
        // Add business logic if needed
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
                .orElseThrow(() -> new RuntimeException("Task not found"));
        // Update task logic
        return taskRepository.save(updatedTask);
    }

    public User assignRoleToUser(Long userId, Long roleId) {
        // Implement logic to assign role to user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Assign role to user logic
        return userRepository.save(user);
    }

    public User updateUserRole(Long userId, Long roleId) {
        // Implement logic to update user's role
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Update user's role logic
        return userRepository.save(user);
    }
}
