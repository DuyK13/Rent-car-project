package com.iuh.rencar_project.utils.validator;

import com.iuh.rencar_project.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 3:03:02 PM
 * @version 0.1
 */
@PropertySource(ignoreResourceNotFound = true, value = "classpath:validation.properties")
@Component
public class GenericValidator implements Validator {

	private final LoginRequestValidator loginRequestValidator;

	@Autowired
	public GenericValidator(LoginRequestValidator loginRequestValidator) {
		this.loginRequestValidator = loginRequestValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return (LoginRequest.class.equals(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof LoginRequest) {
			ValidationUtils.invokeValidator(loginRequestValidator, target, errors);
		}
	}

}
