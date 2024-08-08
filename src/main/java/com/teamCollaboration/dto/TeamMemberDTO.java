package com.teamCollaboration.dto;

public class TeamMemberDTO {
	
    private Long id;
    private String name;
    private String email;
    private String status;
	private String jobTitle;
	
	public TeamMemberDTO() {
		
	}

	public TeamMemberDTO(Long id, String name, String email, String status, String jobTitle) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.status = status;
		this.jobTitle = jobTitle;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	
	

}
