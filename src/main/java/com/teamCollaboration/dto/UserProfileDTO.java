package com.teamCollaboration.dto;

public class UserProfileDTO {
	    private Long id;
	    private String firstname;
	    private String lastname;
	    private String email;
	    private String password;
	    
	    public UserProfileDTO() {
			// TODO Auto-generated constructor stub
		}

		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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



		public UserProfileDTO(Long id, String firstname, String lastname, String email, String password) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.password = password;
		}
	    
		
	    
}
