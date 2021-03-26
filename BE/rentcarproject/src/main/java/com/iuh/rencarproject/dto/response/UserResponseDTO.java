/**
 * @author DuyTT10
 * @date Mar 24, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.dto.response;

import com.iuh.rencarproject.entity.Role;
import com.iuh.rencarproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO implements ResponseDTO {

	private Long id;
	
	private String name;

	private String phoneNumber;

	private String email;

	private String userName;

	private String passWord;

	private Role role;

	public UserResponseDTO(User u) {
		this(u.getId(), u.getName(), u.getPhoneNumber(), u.getEmail(), u.getUsername(), u.getPassword(), u.getRole());
	}
}
