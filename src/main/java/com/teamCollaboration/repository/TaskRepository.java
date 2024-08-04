package com.teamCollaboration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamCollaboration.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
