/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper.helper;

import org.springframework.stereotype.Component;

import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.mapper.annotation.StatusDecoderMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StatusEncoderMapping;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnumMapper {

	@StatusDecoderMapping
	public int toInt(Status value) {
		return value.ordinal();
	}

	@StatusEncoderMapping
	public Status toStatus(int value) {
		switch (value) {
		case 0:
			return Status.NON_ACTIVE;
		default:
			return Status.ACTIVE;
		}
	}
}
