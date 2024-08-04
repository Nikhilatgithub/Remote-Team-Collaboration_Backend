package com.teamCollaboration.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	    // Getters and Setters
}
