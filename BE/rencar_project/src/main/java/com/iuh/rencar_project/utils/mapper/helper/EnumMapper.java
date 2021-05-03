/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper.helper;

import org.springframework.stereotype.Component;

import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.mapper.annotation.StatusEncoderMapping;

@Component
public class EnumMapper {

	@StatusEncoderMapping
	public Status toStatus(int value) {
		if(value == 0)
			return Status.ACTIVE;
		return Status.INACTIVE;
	}
}
