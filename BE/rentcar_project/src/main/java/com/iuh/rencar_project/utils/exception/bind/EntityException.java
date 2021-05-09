package com.iuh.rencar_project.utils.exception.bind;

public class EntityException extends RuntimeException {

	private static final long serialVersionUID = -5883344875986421488L;

	public EntityException(String message) {
		super(message);
	}

	public EntityException(String message, Throwable e) {
		super(message, e);
	}
}
