/**
 * @author DuyTT10
 * @date Apr 13, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.iuh.rencar_project.dto.request.LoginRequest;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:validation.properties")
@Component
public class GenericValidator implements Validator {

	@Autowired
	private LoginRequestValidator loginRequestValidator;
	
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
