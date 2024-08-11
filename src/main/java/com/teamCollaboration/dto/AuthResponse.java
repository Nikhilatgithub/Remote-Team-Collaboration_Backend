package com.teamCollaboration.dto;

public class AuthResponse {

	private String token;
	private String firstname;
    private String lastname;
    private String email;
    private String role;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

//	public AuthResponse(String token, String firstname, String lastname, String email, String role) {
//		super();
//		this.token = token;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//		this.role = role;
//	}

	
	
	
}
