package com.iuh.rencar_project.utils.exception.bind;

public class AccessDeniedException extends RuntimeException {

	private static final long serialVersionUID = 7770528958281537222L;

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(String message, Throwable e) {
		super(message, e);
	}
}
