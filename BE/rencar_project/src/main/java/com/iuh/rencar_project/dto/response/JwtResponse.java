package com.iuh.rencar_project.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author Trần Thế Duy
 * @datetime May 2, 2021 12:03:22 PM
 * @version 0.1
 */

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -7124195153674918100L;

	private String token;

	private Long id;

	private String username;

	private String email;

	private List<String> roles;

	public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public JwtResponse() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
