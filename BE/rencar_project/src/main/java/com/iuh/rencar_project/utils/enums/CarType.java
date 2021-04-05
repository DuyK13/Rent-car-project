package com.iuh.rencar_project.utils.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CarType {

	MANUAL("Xe số sàn"), AUTO("Xe số tự động");

	private String displayName;

}
