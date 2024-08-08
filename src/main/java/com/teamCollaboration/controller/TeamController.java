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

import com.teamCollaboration.entities.Team;
import com.teamCollaboration.services.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.saveOrUpdateTeam(team);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        Optional<Team> existingTeam = teamService.getTeamById(id);
        if (existingTeam.isPresent()) {
            Team team = existingTeam.get();
            // Update fields based on updatedTeam
            team.setName(updatedTeam.getName());
            team.setDescription(updatedTeam.getDescription());

            // Save updated team entity
            Team savedTeam = teamService.saveOrUpdateTeam(team);
            return ResponseEntity.ok(savedTeam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
