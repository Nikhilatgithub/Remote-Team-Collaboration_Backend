package com.teamCollaboration.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.teamCollaboration.custom_exceptions.TeamCollaborationException;
import com.teamCollaboration.dto.ProjectDTO;
import com.teamCollaboration.dto.TaskDTO;
import com.teamCollaboration.dto.TeamMemberDTO;
import com.teamCollaboration.dto.UserProfileDTO;
import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;
import com.teamCollaboration.entities.User;
import com.teamCollaboration.repository.EmployeeRepository;
import com.teamCollaboration.repository.ProjectRepository;
import com.teamCollaboration.repository.TaskRepository;
import com.teamCollaboration.repository.UserRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
	private ModelMapper modelMapper;
    @Autowired
    public EmployeeService(EmployeeRepository userRepository, TaskRepository taskRepository,
    		ProjectRepository projectRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    // Get currently logged in user (employee)
    private Employee getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee user= userRepository.getUserByEmail(email);
        return user;
    }

    public ProjectDTO getEmployeeProjects() {
        Employee employee = getCurrentUser();
        Long projectId=employee.getTeam().getId();
        Project project = projectRepository.findById(projectId).orElseThrow(
        		()-> new TeamCollaborationException("Project Not Found"));
        
        // Implement logic to fetch projects assigned to the employee
        // Example: return projectRepository.findByTeamMembersContaining(employee);
        return modelMapper.map(project,ProjectDTO.class);
    }

    public List<TeamMemberDTO> getTeamMembers() {
    	  Employee employee = getCurrentUser();
          Long teamId=employee.getTeam().getId();
          List<TeamMemberDTO> teamMembers = 
        		  userRepository.getUsersByTeam(teamId).stream()
        		  .map(user -> 
  				modelMapper.map(user,TeamMemberDTO.class)) //Stream<dto>
  				.collect(Collectors.toList());
        // Implement logic to fetch team members of the employee
        // Example: return userRepository.findByTeam(employee.getTeam());
        return teamMembers;
    }

    public List<TaskDTO> getAssignedTasks() {
        Employee employee = getCurrentUser();
        Long projectId=employee.getTeam().getId();
        List<Task> tasks = taskRepository.getTasksByUser(employee.getId(),projectId);
        // Implement logic to fetch tasks assigned to the employee
        // Example: return taskRepository.findByAssignedTo(employee);
        List<TaskDTO> taskDTOs = new ArrayList<>();
        		
        		tasks.stream().forEach(task->{
        	 TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
             taskDTO.setCreatedByUserId(task.getCreatedBy().getId());
             taskDTO.setCreatedByUserName(task.getCreatedBy().getFirstname()+" "+task.getCreatedBy().getLastname());
             taskDTO.setProjectId(task.getProject().getId());
             taskDTO.setProjectName(task.getProject().getName());
             
             taskDTOs.add(taskDTO);
        }
        );
       
  
        return taskDTOs;
    }

    public TaskDTO updateTaskStatus(Long taskId, String status) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TeamCollaborationException("Task not found"));
        task.setStatus(status);
        // Add more update logic if needed
        taskRepository.save(task);
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        taskDTO.setCreatedByUserId(task.getCreatedBy().getId());
        taskDTO.setCreatedByUserName(task.getCreatedBy().getFirstname()+" "+task.getCreatedBy().getLastname());
        taskDTO.setProjectId(task.getProject().getId());
        taskDTO.setProjectName(task.getProject().getName());
        return taskDTO;
    }

    public UserProfileDTO updateProfile(UserProfileDTO updatedUser) {
        Employee currentUser = getCurrentUser();
        currentUser.setFirstname(updatedUser.getFirstname());
        currentUser.setLastname(updatedUser.getLastname());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPassword(updatedUser.getPassword()); // Add encryption logic if needed
        // Add more update logic if needed
        return modelMapper.map(userRepository.save(currentUser), UserProfileDTO.class);
    }
    
    public String updateUserStatus(String status) {
        Employee currentUser = getCurrentUser();
        currentUser.setStatus(status);
        
        
     
        return userRepository.save(currentUser).getStatus();
    }
}