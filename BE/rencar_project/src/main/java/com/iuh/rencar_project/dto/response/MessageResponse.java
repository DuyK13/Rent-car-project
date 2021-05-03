package com.iuh.rencar_project.dto.response;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 7:30:30 PM
 * @version 0.1
 */

public class MessageResponse {

	private String message;

	public MessageResponse(String message) {
		super();
		this.message = message;
	}

	public MessageResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
