/**
 * @author DuyTT10
 * @date Mar 24, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.response;
public class ErrorResponse extends AppResponse {

	public ErrorResponse(String message) {
		super(false, message);
	}
}

