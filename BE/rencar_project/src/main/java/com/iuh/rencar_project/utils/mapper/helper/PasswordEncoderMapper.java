/**
 * @author trant
 * @created_date Apr 19, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.utils.mapper.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.iuh.rencar_project.utils.mapper.annotation.EncodedMapping;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordEncoderMapper {

	@Autowired
	final PasswordEncoder passwordEncoder;
	
	@EncodedMapping
	public String encode(String value) {
		return passwordEncoder.encode(value);
	}
}
