/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.response;

import java.io.Serializable;
import java.util.List;

public class UserResponse implements Serializable {

	static final long serialVersionUID = 2652264316377550947L;

	private Long id;

	private String username;

	private String status;

	private String email;

	private List<String> roles;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserResponse(Long id, String username, String status, String email, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.status = status;
		this.email = email;
		this.roles = roles;
	}

	public UserResponse() {
		super();
	}

}
