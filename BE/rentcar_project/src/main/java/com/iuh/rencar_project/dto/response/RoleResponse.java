package com.iuh.rencar_project.dto.response;

import java.io.Serializable;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 7:03:49 PM
 * @version 0.1
 */

public class RoleResponse implements Serializable {

	private static final long serialVersionUID = -8289362578477298108L;

	private Long id;

	private String name;

	public RoleResponse(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoleResponse() {
		super();
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

}
