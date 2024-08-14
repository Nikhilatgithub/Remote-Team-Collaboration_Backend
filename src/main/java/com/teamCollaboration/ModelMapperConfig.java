package com.teamCollaboration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teamCollaboration.dto.EmployeeDTO;
import com.teamCollaboration.dto.ProjectDTO;
import com.teamCollaboration.dto.TaskDTO;
import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.Project;
import com.teamCollaboration.entities.Task;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Mapping for Project to ProjectDTO
        modelMapper.addMappings(new PropertyMap<Project, ProjectDTO>() {
            @Override
            protected void configure() {
                map().setTeamId(source.getTeam().getId());
                map().setTeamName(source.getTeam().getName());
                map().setTeamDescription(source.getTeam().getDescription());
            }
        });

        
        // Custom mapping for Employee to EmployeeDto
        modelMapper.addMappings(new PropertyMap<Employee, EmployeeDTO>() {
            @Override
            protected void configure() {
                map().setRoleId(source.getRole().getId());
                map().setRoleName(source.getRole().getName());
                map().setTeamId(source.getTeam().getId());
                map().setTeamName(source.getTeam().getName());
            }
        });
        
        modelMapper.addMappings(new PropertyMap<Task, TaskDTO>() {
            @Override
            protected void configure() {
                map().setCreatedByUserId(source.getCreatedBy().getId());
              //  map().setCreatedByUserName(source.getCreatedBy().getFirstname()+" "+source.getCreatedBy().getLastname());
                map().setCreatedByUserEmail(source.getCreatedBy().getEmail());
                map().setAssignedToUserId(source.getAssignedTo().getId());
             //   map().setAssignedToUserName(source.getAssignedTo().getFirstname()+" "+source.getAssignedTo().getLastname());
                map().setProjectId(source.getProject().getId());
                map().setProjectName(source.getProject().getName());
            }
        });
        
        // Add mappings for other DTOs similarly
//        modelMapper.addMappings(new PropertyMap<OtherEntity, OtherDTO>() {
//            @Override
//            protected void configure() {
//                // Define the mapping logic
//            }
//        });

        return modelMapper;
    }
}

