package com.iuh.rencar_project.utils.validator;

import com.iuh.rencar_project.dto.request.LoginRequest;
import com.iuh.rencar_project.service.template.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 3:03:02 PM
 * @version 0.1
 */
@Component
public class LoginRequestValidator implements Validator {

	private final IUserService userService;

	@Autowired
	public LoginRequestValidator(IUserService userService) {
		this.userService = userService;
	}

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
		} else if(!userService.isUserActive(loginRequest.getUsername())){
			errors.rejectValue("username", "username.inactive", "User " + loginRequest.getUsername() + " is not active or block");
		}
	}

}
