package com.iuh.rencar_project.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iuh.rencar_project.dto.request.LoginRequest;
import com.iuh.rencar_project.service.template.IUserService;

@Component
public class LoginRequestValidator implements Validator {

	@Autowired
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginRequest.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginRequest loginRequest = (LoginRequest) target;
		if (!userService.existsByUsername(loginRequest.getUsername())) {
			errors.rejectValue("username", "username.notFound", "User " + loginRequest.getUsername() + " not found");
		} else if (!userService.isRightPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
			errors.rejectValue("password", "password.wrong", "User " + loginRequest.getUsername() + " wrong password");
		}
	}

}
