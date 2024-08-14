package com.teamCollaboration.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.custom_exceptions.TeamCollaborationException;
import com.teamCollaboration.dto.EmployeeDTO;
import com.teamCollaboration.dto.ProjectDTO;
import com.teamCollaboration.dto.TaskDTO;
import com.teamCollaboration.dto.TeamDTO;
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
    private  ModelMapper modelMapper;

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
    
    public List<ProjectDTO> projectList() {
        // Add business logic if needed
    	
        return projectRepository.findAll().stream().map(project->
        modelMapper.map(project, ProjectDTO.class))
        .collect(Collectors.toList());
        //modelMapper.map(teamRepository.save(newTeam),TeamDTO.class);
    }
    
    public List<EmployeeDTO> usersList() {
        // Add business logic if needed
    	
        return userRepository.findAll().stream().map(user->
        
        modelMapper.map(user, EmployeeDTO.class))
        .collect(Collectors.toList());
        //modelMapper.map(teamRepository.save(newTeam),TeamDTO.class);
    }

    public ProjectDTO updateProject(Long projectId, ProjectDTO updatedProject) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new TeamCollaborationException("Project not found"));
        // Update project logic
       Project newproject = modelMapper.map(updatedProject,Project.class);
       newproject.setId(project.getId());
        return modelMapper.map( projectRepository.save(newproject),ProjectDTO.class);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public EmployeeDTO addUser(EmployeeDTO user) {
        // Add business logic if needed
    	Employee newUser=modelMapper.map(user, Employee.class);
        return modelMapper.map(userRepository.save(newUser),EmployeeDTO.class);
    }

    public EmployeeDTO updateUser(Long userId, EmployeeDTO updatedUser) {
    	Employee user = userRepository.findById(userId)
                .orElseThrow(() -> new TeamCollaborationException("User not found"));
        // Update user logic
    	Employee newemp = modelMapper.map(updatedUser,Employee.class);
    	newemp.setPassword(user.getPassword());
    	newemp.setId(user.getId());
        return modelMapper.map(userRepository.save(newemp),EmployeeDTO.class);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public TaskDTO addTask(TaskDTO task) {
        // Add business logic if needed
    	Task newTask = modelMapper.map(task, Task.class);
        return modelMapper.map(taskRepository.save(newTask), TaskDTO.class) ;
    }
    
    public List<TaskDTO> taskList() {
        
    return taskRepository.findAll().stream().map(task-> 
        modelMapper.map(task, TaskDTO.class))
        .collect(Collectors.toList());
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskDTO updateTask(Long taskId, TaskDTO updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TeamCollaborationException("Task not found"));
        // Update task logic
        Task newTask = modelMapper.map(updatedTask, Task.class);
        newTask.setId(taskId);
        return modelMapper.map(taskRepository.save(newTask), TaskDTO.class) ;
        
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
