/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.exception.bind.user;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7364331339930707216L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
