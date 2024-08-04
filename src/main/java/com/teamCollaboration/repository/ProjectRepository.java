package com.teamCollaboration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamCollaboration.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{

}
