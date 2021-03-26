/**
 * @author DuyTT10
 * @date Mar 24, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AppResponse {

	private Boolean success;
	private String message;
}
