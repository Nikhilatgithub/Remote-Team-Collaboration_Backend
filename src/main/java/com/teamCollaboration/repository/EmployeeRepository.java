package com.teamCollaboration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamCollaboration.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
