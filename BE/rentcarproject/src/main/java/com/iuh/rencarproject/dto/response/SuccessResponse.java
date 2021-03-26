/**
 * @author DuyTT10
 * @date Mar 24, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SuccessResponse<T> extends AppResponse {

	private T data;

	public SuccessResponse(String message, T t) {
		super(true, message);
		this.data = t;
	}
	
	public SuccessResponse(String message) {
		super(true, message);
		this.data = null;
	}
	

	public SuccessResponse(T t) {
		super(true, "");
		this.data = t;
	}
	
	public SuccessResponse() {
		super(true, "");
		this.data = null;
	}
}
