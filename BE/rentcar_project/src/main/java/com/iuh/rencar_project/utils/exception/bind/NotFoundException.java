package com.iuh.rencar_project.utils.exception.bind;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1438104308291920655L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable e) {
		super(message, e);
	}
}
