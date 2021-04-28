/**
 * @author trant
 * @created_date Apr 22, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iuh.rencar_project.dto.request.PasswordRequestDTO;
import com.iuh.rencar_project.service.template.IUserService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:validation.properties")
@Component
public class PasswordRequestDTOValidator implements Validator {

	@Value("${Validator.password.wrong}")
	private String wrongPassword;

	@Autowired
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PasswordRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordRequestDTO passwordRequestDTO = (PasswordRequestDTO) target;
		if (!userService.existsByIdAndPassword(passwordRequestDTO.getId(), passwordRequestDTO.getOldPassword())) {
			errors.rejectValue("oldPassword", "Validator.password.wrong", wrongPassword);
		}

	}

}
