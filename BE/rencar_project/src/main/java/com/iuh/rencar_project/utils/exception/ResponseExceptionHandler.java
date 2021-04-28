/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.exception;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.iuh.rencar_project.dto.ErrorDTO;
import com.iuh.rencar_project.utils.exception.bind.InvalidInputException;
import com.iuh.rencar_project.utils.exception.bind.role.RoleNotFoundException;
import com.iuh.rencar_project.utils.exception.bind.user.UserException;
import com.iuh.rencar_project.utils.exception.bind.user.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
	private final LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));

	private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

	private final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<String> listEx = result.getAllErrors().stream().map(x -> {
			return x.getDefaultMessage();
		}).collect(Collectors.toList());
		ErrorDTO response = new ErrorDTO(String.join(", ", listEx), BAD_REQUEST, now);
		log.error("Method Argument Not Valid: ", ex);
		return new ResponseEntity<Object>(response, BAD_REQUEST);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		ErrorDTO response = new ErrorDTO(ex.getMessage(), BAD_REQUEST, now);
		log.error("Invalid Input Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(response, BAD_REQUEST);
	}

	@ExceptionHandler(RoleNotFoundException.class)
	public ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex) {
		ErrorDTO response = new ErrorDTO(ex.getMessage(), NOT_FOUND, now);
		log.error("Role Not Found Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(response, NOT_FOUND);
	}

	// User
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Object> handleUserException(UserException ex) {
		ErrorDTO response = new ErrorDTO(ex.getMessage(), BAD_REQUEST, now);
		log.error("User Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(response, BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
		ErrorDTO response = new ErrorDTO(ex.getMessage(), NOT_FOUND, now);
		log.error("User Not Found Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(response, NOT_FOUND);
	}
}
