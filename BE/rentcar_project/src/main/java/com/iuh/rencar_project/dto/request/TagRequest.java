package com.iuh.rencar_project.dto.request;

public class TagRequest {

	private String name;

	public TagRequest() {
		super();
	}

	public TagRequest(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
