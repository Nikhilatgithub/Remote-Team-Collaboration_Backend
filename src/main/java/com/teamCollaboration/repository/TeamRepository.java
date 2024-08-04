package com.teamCollaboration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamCollaboration.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
