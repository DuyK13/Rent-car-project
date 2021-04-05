///**
// * @author DuyTT10
// * @date Mar 23, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.iuh.rencar_project.dto.request.UserRequestDTO;
//import com.iuh.rencar_project.dto.response.UserResponseDTO;
//import com.iuh.rencar_project.entity.Role;
//import com.iuh.rencar_project.entity.User;
//import com.iuh.rencar_project.repository.RoleRepository;
//import com.iuh.rencar_project.repository.UserRepository;
//import com.iuh.rencar_project.service.template.IUserService;
//
//@Service
//public class UserServiceImpl implements IUserService {
//
//	final static Logger logger = Logger.getLogger(UserServiceImpl.class);
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Override
//	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
//		UserResponseDTO result = null;
//		try {
//			Role role = roleRepository.findByName(userRequestDTO.getRoleName());
//			User user = userRequestDTO.convertUserCreateToEntity(role);
//			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//			user = userRepository.saveAndFlush(user);
//			result = new UserResponseDTO(user);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public UserResponseDTO findById(Long id) {
//		UserResponseDTO result = null;
//		try {
//			User user = userRepository.findById(id).get();
//			result = new UserResponseDTO(user);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public boolean deleteById(Long id) {
//		try {
//			userRepository.deleteById(id);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return !userRepository.existsById(id);
//	}
//
//	@Override
//	public List<UserResponseDTO> findAll() {
//		List<UserResponseDTO> users = new ArrayList<UserResponseDTO>();
//		try {
//			users = userRepository.findAll(Sort.by(Order.asc("username"))).stream().map(x -> {
//				return new UserResponseDTO(x);
//			}).collect(Collectors.toList());
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return users;
//	}
//
//	@Override
//	public User findByUserName(String username) {
//		User result = null;
//		try {
//			result = userRepository.findByUsername(username);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public List<UserResponseDTO> findAllPaging(Pageable pageRequest) {
//		List<UserResponseDTO> result = null;
//		try {
//			Page<User> users = userRepository.findAll(pageRequest);
//			result = users.getContent().stream().map(x -> {
//				return new UserResponseDTO(x);
//			}).collect(Collectors.toList());
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
//		UserResponseDTO result = null;
//		try {
//			User user = userRepository.findById(userId).get();
//			user = userRequestDTO.convertUserUpdateToEntity(user);
//			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//			user = userRepository.saveAndFlush(user);
//			result = new UserResponseDTO(user);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public UserResponseDTO getDetailByLogin(String username, String password) {
//		UserResponseDTO result = null;
//		try {
//			User user = userRepository.findByUsername(username);
//			result = new UserResponseDTO(user);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public boolean existsByUsername(String username) {
//		return userRepository.existsByUsername(username);
//	}
//
//	@Override
//	public boolean existsByEmailAndEmailNotNull(String email) {
//		return userRepository.existsByEmailAndEmailNotNull(email);
//	}
//
//	@Override
//	public boolean existsByPhoneNumberNotNull(String phoneNumber) {
//		return userRepository.existsByPhoneNumberAndPhoneNumberNotNull(phoneNumber);
//	}
//
//	@Override
//	public boolean checkUserPassword(String username, String password) {
//		User user = userRepository.findByUsername(username);
//		return bCryptPasswordEncoder.matches(password, user.getPassword());
//
//	}
//}
