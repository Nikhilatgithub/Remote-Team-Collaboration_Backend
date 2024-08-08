package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Role;
import com.teamCollaboration.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Method to save or update a role
    public Role saveOrUpdateRole(Role role) {
        return roleRepository.save(role);
    }

    // Method to retrieve all roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Method to find role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Method to delete role by ID
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}