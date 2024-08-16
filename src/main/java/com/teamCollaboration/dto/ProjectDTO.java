package com.teamCollaboration.dto;

import java.time.LocalDate;
import java.util.Date;

import com.teamCollaboration.entities.Team;

public class ProjectDTO {
	 	
		private Long id;
	    private String name;
	    private String description;
	    private LocalDate startDate;
	    private LocalDate endDate;
	    private String status;
	    private Long teamId;
	    private String teamName;
	    private String TeamDescription;
	    
	    
	    
		public ProjectDTO(Long id, String name, String description, LocalDate startDate, LocalDate endDate, String status,
				Long teamId, String teamName, String teamDescription) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.startDate = startDate;
			this.endDate = endDate;
			this.status = status;
			this.teamId = teamId;
			this.teamName = teamName;
			TeamDescription = teamDescription;
		}
		public Long getTeamId() {
			return teamId;
		}
		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}
		public String getTeamName() {
			return teamName;
		}
		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}
		public String getTeamDescription() {
			return TeamDescription;
		}
		public void setTeamDescription(String teamDescription) {
			TeamDescription = teamDescription;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public LocalDate getEndDate() {
			return endDate;
		}
		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	    
	    public ProjectDTO() {
			// TODO Auto-generated constructor stub
		}
		
	    
	    
}
