package com.iuh.rencar_project.dto.request;

/**
 * @author Trần Thế Duy
 * @datetime May 2, 2021 11:47:05 AM
 * @version 0.1
 */

public class LoginRequest {

	private String username;

	private String password;

	public LoginRequest() {
		super();
	}

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
