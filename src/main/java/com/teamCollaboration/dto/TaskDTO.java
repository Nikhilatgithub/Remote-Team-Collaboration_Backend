package com.teamCollaboration.dto;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.teamCollaboration.entities.Employee;
import com.teamCollaboration.entities.Project;

public class TaskDTO {
	
	 	private Long id;
	    private String title;
	    private String description;
	    private String status;
	    private String priority;
	    private Date startDate;
	    private Date dueDate;
	    private Long createdByUserId;
	    private String createdByUserName;
	    private String createdByUserEmail;
	    private Long assignedToUserId;
	    private String assignedToUserName;
	    private Long projectId;
	    private String projectName;


	    public TaskDTO(Long id, String title, String description, String status, String priority, Date startDate,
				Date dueDate, Long createdByUserId, String createdByUserName, String createdByUserEmail,
				Long assignedToUserId, String assignedToUserName, Long projectId, String projectName) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.status = status;
			this.priority = priority;
			this.startDate = startDate;
			this.dueDate = dueDate;
			this.createdByUserId = createdByUserId;
			this.createdByUserName = createdByUserName;
			this.createdByUserEmail = createdByUserEmail;
			this.assignedToUserId = assignedToUserId;
			this.assignedToUserName = assignedToUserName;
			this.projectId = projectId;
			this.projectName = projectName;
		}


		public Long getAssignedToUserId() {
			return assignedToUserId;
		}


		public void setAssignedToUserId(Long assignedToUserId) {
			this.assignedToUserId = assignedToUserId;
		}


		public String getAssignedToUserName() {
			return assignedToUserName;
		}


		public void setAssignedToUserName(String assignedToUserName) {
			this.assignedToUserName = assignedToUserName;
		}


		public TaskDTO() {
			// TODO Auto-generated constructor stub
		}


		


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getPriority() {
			return priority;
		}


		public void setPriority(String priority) {
			this.priority = priority;
		}


		public Date getStartDate() {
			return startDate;
		}


		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}


		public Date getDueDate() {
			return dueDate;
		}


		public void setDueDate(Date dueDate) {
			this.dueDate = dueDate;
		}


		public Long getCreatedByUserId() {
			return createdByUserId;
		}


		public void setCreatedByUserId(Long createdByUserId) {
			this.createdByUserId = createdByUserId;
		}


		public String getCreatedByUserName() {
			return createdByUserName;
		}


		public void setCreatedByUserName(String createdByUserName) {
			this.createdByUserName = createdByUserName;
		}


		public String getCreatedByUserEmail() {
			return createdByUserEmail;
		}


		public void setCreatedByUserEmail(String createdByUserEmail) {
			this.createdByUserEmail = createdByUserEmail;
		}


		public Long getProjectId() {
			return projectId;
		}


		public void setProjectId(Long projectId) {
			this.projectId = projectId;
		}


		public String getProjectName() {
			return projectName;
		}


		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
	    
	    
}
