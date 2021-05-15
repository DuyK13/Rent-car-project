package com.iuh.rencar_project.utils.mapper.helper;

import com.iuh.rencar_project.utils.mapper.annotation.PasswordEncodedMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 3:03:02 PM
 * @version 0.1
 */
@Component
public class PasswordEncoderMapper {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@PasswordEncodedMapping
	public String encode(String value) {
		return passwordEncoder.encode(value);
	}
}
