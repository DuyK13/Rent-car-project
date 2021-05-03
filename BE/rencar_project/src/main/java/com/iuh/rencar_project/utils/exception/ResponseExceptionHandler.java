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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuh.rencar_project.dto.response.ErrorResponse;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.InvalidInputException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	private final static Logger logger = LogManager.getLogger(ResponseExceptionHandler.class);

	private final LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));

	private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

	private final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> listEx = bindingResult.getAllErrors().stream().map(x -> {
			return x.getDefaultMessage();
		}).collect(Collectors.toList());
		ErrorResponse response = new ErrorResponse(String.join(", ", listEx), BAD_REQUEST, now);
		logger.error("Method Argument Not Valid: ", ex);
		String result = "";
		try {
			result = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(result, BAD_REQUEST);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage(), BAD_REQUEST, now);
		logger.error("Invalid Input Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(response, BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) throws JsonProcessingException {
		ErrorResponse response = new ErrorResponse(ex.getMessage(), NOT_FOUND, now);
		logger.error("Not Found Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(objectMapper.writeValueAsString(response), NOT_FOUND);
	}

	@ExceptionHandler(EntityException.class)
	public ResponseEntity<Object> handleEntityException(EntityException ex) throws JsonProcessingException {
		ErrorResponse response = new ErrorResponse(ex.getMessage(), BAD_REQUEST, now);
		logger.error("Entity Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(objectMapper.writeValueAsString(response), BAD_REQUEST);
	}

}
