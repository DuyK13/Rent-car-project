/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.exception.bind;

public class InvalidInputException extends RuntimeException{

	private static final long serialVersionUID = 1108681802385280179L;

	public InvalidInputException(String message) {
		super(message);
	}
}
