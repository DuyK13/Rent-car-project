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
//public class UserControllerValidator implements Validator {
//
//	@Autowired
//	private IUserService userService;
//
//	@Value("${Validator.username.duplicate}")
//	private String duplicateUsername;
//	
//	@Value("${Validator.phoneNumber.duplicate}")
//	private String duplicatePhoneNumber;
//	
//	@Value("${Validator.email.duplicate}")
//	private String duplicateEmail;
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
//		if (userService.existsByUsername(user.getUsername())) {
//			errors.rejectValue("username", "Duplicate.username", duplicateUsername);
//		}
//		
//		if (userService.existsByPhoneNumberNotNull(user.getPhoneNumber())) {
//			errors.rejectValue("username", "Duplicate.phoneNumber", duplicatePhoneNumber);
//		}
//		
//		if (userService.existsByEmailAndEmailNotNull(user.getEmail())) {
//			errors.rejectValue("username", "Duplicate.email", duplicateEmail);
//		}
//	}
//}
