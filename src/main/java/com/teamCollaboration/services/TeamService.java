package com.teamCollaboration.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamCollaboration.entities.Team;
import com.teamCollaboration.repository.TeamRepository;

@Service
public class TeamService {
	 private final TeamRepository teamRepository;

	    @Autowired
	    public TeamService(TeamRepository teamRepository) {
	        this.teamRepository = teamRepository;
	    }

	    // Method to save or update a team
	    public Team saveOrUpdateTeam(Team team) {
	        return teamRepository.save(team);
	    }

	    // Method to retrieve all teams
	    public List<Team> getAllTeams() {
	        return teamRepository.findAll();
	    }

	    // Method to find team by ID
	    public Optional<Team> getTeamById(Long id) {
	        return teamRepository.findById(id);
	    }

	    // Method to delete team by ID
	    public void deleteTeamById(Long id) {
	        teamRepository.deleteById(id);
	    }
}
