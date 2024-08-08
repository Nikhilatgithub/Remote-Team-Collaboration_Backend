package com.teamCollaboration.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamCollaboration.entities.Role;
import com.teamCollaboration.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveOrUpdateRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role updatedRole) {
        Optional<Role> existingRole = roleService.getRoleById(id);
        if (existingRole.isPresent()) {
            Role role = existingRole.get();
            // Update fields based on updatedRole
            role.setName(updatedRole.getName());

            // Save updated role entity
            Role savedRole = roleService.saveOrUpdateRole(role);
            return ResponseEntity.ok(savedRole);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}