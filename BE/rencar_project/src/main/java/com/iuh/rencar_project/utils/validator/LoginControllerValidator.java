///**
// * @author DuyTT10
// * @date Mar 25, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.utils.validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//import com.iuh.rencar_project.dto.request.UserRequestDTO;
//import com.iuh.rencar_project.service.template.IUserService;
//
//@PropertySource(ignoreResourceNotFound = true, value = "classpath:validation.properties")
//@Component
//public class LoginControllerValidator implements Validator {
//
//	@Autowired
//	private IUserService userService;
//
//	@Value("${Validator.username.notFound}")
//	private String usernameNotFound;
//
//	@Value("${Validator.password.wrong}")
//	private String passwordWrong;
//
//	@Override
//	public boolean supports(Class<?> aClass) {
//		return UserRequestDTO.class.equals(aClass);
//	}
//
//	@Override
//	public void validate(Object o, Errors errors) {
//		UserRequestDTO user = (UserRequestDTO) o;
//
//		if (!userService.existsByUsername(user.getUsername()))
//			errors.rejectValue("username", "Validator.username.notFound", usernameNotFound);
//		else if (!userService.checkUserPassword(user.getUsername(), user.getPassword()))
//			errors.rejectValue("username", "Validator.password.wrong", passwordWrong);
//	}
//}
