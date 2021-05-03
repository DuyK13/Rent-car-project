package com.iuh.rencar_project.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;


/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 4:08:17 PM
 * @version 0.1
 */

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1334153051435798281L;

	private String message;

	private HttpStatus status;

	private LocalDateTime timestamp;

	public ErrorResponse(String message, HttpStatus status, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	public ErrorResponse() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
