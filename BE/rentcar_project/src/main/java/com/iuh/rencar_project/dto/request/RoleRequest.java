package com.iuh.rencar_project.dto.request;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 7:09:41 PM
 * @version 0.1
 */

public class RoleRequest {

	private String name;

	public RoleRequest(String name) {
		super();
		this.name = name;
	}

	public RoleRequest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
