package com.teamCollaboration.dto;

public class AuthResponse {

	private String token;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthResponse(String token) {
		super();
		this.token = token;
	}
	
	
}
