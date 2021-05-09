package com.iuh.rencar_project.utils.enums;

public enum CarType {

	MANUAL("Xe số sàn"), AUTO("Xe số tự động");

	private final String displayName;

	CarType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
