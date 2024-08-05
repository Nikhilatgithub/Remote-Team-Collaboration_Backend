package com.teamCollaboration.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends User {
	  
	    private String jobTitle;
	   
	    @ManyToOne
	    @JoinColumn(name = "team_id")
	    private Team team;
	    // Getters and Setters

		public String getJobTitle() {
			return jobTitle;
		}

		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public Team getTeam() {
			return team;
		}

		public void setTeam(Team team) {
			this.team = team;
		}
	    
	    
}
