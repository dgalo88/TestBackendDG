package com.dg.testbackend.client.dto;

public class UserDTO {

	private Long id;

	private String firstname;

	private String lastname;

	private String email;

	private String username;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String firstname,
			String lastname, String email, String username) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format(
				"UserEntity [id=%s, firstname=%s, lastname=%s, email=%s, username=%s]",
				id, firstname, lastname, email, username);
	}

}
