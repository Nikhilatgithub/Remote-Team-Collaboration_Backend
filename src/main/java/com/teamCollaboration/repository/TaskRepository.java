package com.teamCollaboration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamCollaboration.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("select t from Task t where t.assignedTo.id = :user_id AND t.project.id = :projectId")
	public List<Task> getTasksByUser(@Param("user_id") Long user_id, @Param("projectId") Long projectId);
}
