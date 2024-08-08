package com.teamCollaboration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teamCollaboration.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query("select u from Employee u where u.email = :email")
	public Employee getUserByEmail(@Param("email") String email);
	
	@Query("select u from Employee u where u.team.id = :team_id")
	public List<Employee> getUsersByTeam(@Param("team_id") Long team_id);
}
