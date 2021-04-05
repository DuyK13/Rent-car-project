///**
// * @author DuyTT10
// * @date Mar 24, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.dto.response;
//
//import java.io.Serializable;
//
//import com.iuh.rencar_project.entity.Role;
//import com.iuh.rencar_project.entity.User;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class UserResponseDTO implements Serializable {
//
//	private static final long serialVersionUID = -6782192779481372906L;
//
//	private Long id;
//	
//	private String name;
//
//	private String phoneNumber;
//
//	private String email;
//
//	private String userName;
//
//	private String passWord;
//
//	private Role role;
//
//	public UserResponseDTO(User u) {
//		this(u.getId(), u.getName(), u.getPhoneNumber(), u.getEmail(), u.getUsername(), u.getPassword(), u.getRole());
//	}
//}
