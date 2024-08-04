package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Project;
import com.teamCollaboration.repository.ProjectRepository;

@Service
public class ProjectService {
	
	 private ProjectRepository projectRepository;

	    @Autowired
	    public ProjectService(ProjectRepository projectRepository) {
	        this.projectRepository = projectRepository;
	    }

	    // Method to save or update a project
	    public Project saveOrUpdateProject(Project project) {
	        return projectRepository.save(project);
	    }

	    // Method to retrieve all projects
	    public List<Project> getAllProjects() {
	        return projectRepository.findAll();
	    }

	    // Method to find project by ID
	    public Optional<Project> getProjectById(Long id) {
	        return projectRepository.findById(id);
	    }

	    // Method to delete project by ID
	    public void deleteProjectById(Long id) {
	        projectRepository.deleteById(id);
	    }
}
