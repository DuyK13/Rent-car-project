/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.exception.bind.role;

public class RoleNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -8735913038077929973L;

	public RoleNotFoundException(String message) {
		super(message);
	}
}
