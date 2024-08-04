package com.teamCollaboration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamCollaboration.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
