///**
// * @author DuyTT10
// * @date Mar 25, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.dto.request;
//
//import com.iuh.rencar_project.entity.Role;
//import com.iuh.rencar_project.entity.User;
//
//import lombok.Data;
//
//@Data
//public class UserRequestDTO {
//
//	private String name;
//
//	private String phoneNumber;
//
//	private String email;
//
//	private String username;
//
//	private String password;
//
//	private String roleName;
//
//	public User convertUserLoginToEntity() {
//		User user = new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		return user;
//	}
//
//	public User convertUserUpdateToEntity(User user) {
//		user.setName(name);
//		user.setPhoneNumber(phoneNumber);
//		user.setEmail(email);
//		user.setPassword(password);
//		return user;
//	}
//
//	public User convertUserCreateToEntity(Role role) {
//		User user = new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		user.setRole(role);
//		return user;
//	}
//}
