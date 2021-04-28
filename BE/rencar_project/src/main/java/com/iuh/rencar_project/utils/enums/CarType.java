package com.iuh.rencar_project.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CarType {

	MANUAL("Xe số sàn"), AUTO("Xe số tự động");

	private String displayName;

}
