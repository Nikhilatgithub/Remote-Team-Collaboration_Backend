package com.teamCollaboration.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.teamCollaboration.custom_exceptions.TeamCollaborationException;
import com.teamCollaboration.dto.ProjectDTO;
import com.teamCollaboration.dto.TeamDTO;
import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.Team;
import com.teamCollaboration.repository.EmployeeRepository;
import com.teamCollaboration.repository.ProjectRepository;
import com.teamCollaboration.repository.TaskRepository;
import com.teamCollaboration.repository.TeamRepository;

@Service
public class ManagerService {
	   
	    private  ProjectRepository projectRepository;
	    private  TeamRepository teamRepository;
	    private  TaskRepository taskRepository;
	    private EmployeeRepository userRepository;
	    private  ModelMapper modelMapper;

	    @Autowired
	    public ManagerService(ModelMapper modelMapper,EmployeeRepository userRepository,ProjectRepository projectRepository, TeamRepository teamRepository, TaskRepository taskRepository) {
	        this.projectRepository = projectRepository;
	        this.teamRepository = teamRepository;
	        this.taskRepository = taskRepository;
	        this.userRepository =  userRepository;
	        this.modelMapper = modelMapper;
	    }

	 // Get currently logged in user (employee)
	    private Employee getCurrentUser() {
	        String email = SecurityContextHolder.getContext().getAuthentication().getName();
	        Employee user= userRepository.getUserByEmail(email);
	        return user;
	    }
	    
	    public List<ProjectDTO> getAllProjects() {
	        return projectRepository.findAll().stream().map(project->
	        modelMapper.map(project, ProjectDTO.class))
	        .collect(Collectors.toList());
	    }
	    
	    public List<TeamDTO> getTeamsList() {
	        return teamRepository.findAll().stream().map(team->
	        modelMapper.map(team, TeamDTO.class))
	        .collect(Collectors.toList());
	    }

	    public ProjectDTO createProject(ProjectDTO project) {
	        // Add business logic if needed
	    	Project newproject = modelMapper.map(project, Project.class);
	    	projectRepository.save(newproject);
	        return modelMapper.map(projectRepository.save(newproject), ProjectDTO.class) ;
	    }

	    public TeamDTO createTeam(TeamDTO team) {
	        // Add business logic if needed
	    	Team newTeam = modelMapper.map(team, Team.class);
	        return modelMapper.map(teamRepository.save(newTeam),TeamDTO.class);
	    }

	    public String addTeamMembers(Long teamId, List<Long> memberIds) {
	        // Implement logic to add members to a team
	        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
	       
	        memberIds.stream().forEach(id->{
	        	 Employee user = userRepository.findById(id).orElseThrow(
	        			()-> new TeamCollaborationException("User not found with id "+id));
	        	 user.setTeam(team);
	        	 userRepository.save(user);
	        });
	       
	        // Add members to team logic
	        return "done";
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
