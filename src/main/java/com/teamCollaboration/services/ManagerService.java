package com.teamCollaboration.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.Team;
import com.teamCollaboration.repository.ProjectRepository;
import com.teamCollaboration.repository.TaskRepository;
import com.teamCollaboration.repository.TeamRepository;

@Service
public class ManagerService {
	   
	    private  ProjectRepository projectRepository;
	    private  TeamRepository teamRepository;
	    private  TaskRepository taskRepository;

	    @Autowired
	    public ManagerService(ProjectRepository projectRepository, TeamRepository teamRepository, TaskRepository taskRepository) {
	        this.projectRepository = projectRepository;
	        this.teamRepository = teamRepository;
	        this.taskRepository = taskRepository;
	    }

	    public List<Project> getAllProjects() {
	        return projectRepository.findAll();
	    }

	    public Project createProject(Project project) {
	        // Add business logic if needed
	        return projectRepository.save(project);
	    }

	    public Team createTeam(Team team) {
	        // Add business logic if needed
	        return teamRepository.save(team);
	    }

	    public Team addTeamMembers(Long teamId, List<Long> memberIds) {
	        // Implement logic to add members to a team
	        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
	        // Add members to team logic
	        return teamRepository.save(team);
	    }

	    public Task createTask(Task task) {
	        // Add business logic if needed
	        return taskRepository.save(task);
	    }

	    public Task updateTask(Long taskId, Task updatedTask) {
	        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        // Update task logic
	        return taskRepository.save(updatedTask);
	    }
	}
