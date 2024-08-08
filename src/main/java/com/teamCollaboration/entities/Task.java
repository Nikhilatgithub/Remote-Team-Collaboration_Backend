package com.teamCollaboration.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String title;
	    private String description;
	    private String status;
	    private String priority;
	    @DateTimeFormat(pattern="dd/MM/yyyy")
	    private Date startDate;
	    @DateTimeFormat(pattern="dd/MM/yyyy")
	    private Date dueDate;

	    @ManyToOne
	    @JoinColumn(name = "created_by_id")
	    private Employee createdBy;

	    @ManyToOne
	    @JoinColumn(name = "assigned_to_id")
	    private Employee assignedTo;

	    @ManyToOne
	    @JoinColumn(name = "project_id")
	    private Project project;

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

		public Employee getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(Employee createdBy) {
			this.createdBy = createdBy;
		}

		public Employee getAssignedTo() {
			return assignedTo;
		}

		public void setAssignedTo(Employee assignedTo) {
			this.assignedTo = assignedTo;
		}

		public Project getProject() {
			return project;
		}

		public void setProject(Project project) {
			this.project = project;
		}

		public Task(Long id, String title, String description, String status, String priority, Date startDate,
				Date dueDate, Employee createdBy, Employee assignedTo, Project project) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.status = status;
			this.priority = priority;
			this.startDate = startDate;
			this.dueDate = dueDate;
			this.createdBy = createdBy;
			this.assignedTo = assignedTo;
			this.project = project;
		}

		public Task() {
			
		}

	    // Getters and Setters
	    
	    
	    
	    
}
