/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.repository.UserRepository;
import com.iuh.rencar_project.service.template.IUserService;
import com.iuh.rencar_project.utils.enums.ERole;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IUserMapper;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUserMapper userMapper;

	@Override
	public String save(UserRequest userRequest) {
		String username = userRequest.getUsername();
		if (this.existsByUsername(username))
			throw new EntityException("User " + username + " exists");
		try {
			System.out.println(userMapper.toEntity(userRequest));
			userRepository.saveAndFlush(userMapper.toEntity(userRequest));
		} catch (Exception e) {
			logger.error("User Exception: ", e);
			throw new EntityException("User " + username + " save fail", e);
		}
		return "User " + username + " save success";
	}

	@Override
	public String update(Long id, UserRequest userRequest) {
		User currentUser = this.findById(id);
		String currentUsername = currentUser.getUsername();
		if (this.existsByUsername(userRequest.getUsername()) && !currentUsername.equals(userRequest.getUsername()))
			throw new EntityException("User " + userRequest.getUsername() + " exists");
		try {
			userMapper.updateUserInformation(userRequest, currentUser);
			userRepository.saveAndFlush(currentUser);
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			return "User " + currentUsername + " update fail";
		}
		return "User " + currentUsername + " update success";
	}

	@Override
	public String update(Long id) {
		User currentUser = this.findById(id);
		String username = currentUser.getUsername();
		String message;
		try {
			Status status = currentUser.getStatus();
			if (status == Status.ACTIVE) {
				currentUser.setStatus(Status.INACTIVE);
				userRepository.saveAndFlush(currentUser);
				message = "User " + username + " inactive success";
			} else {
				currentUser.setStatus(Status.ACTIVE);
				userRepository.saveAndFlush(currentUser);
				message = "User " + username + " active success";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EntityException("User " + username + " change status fail");
		}
		return message;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"));
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("User " + username + " not found"));
	}

	@Override
	public Page<User> findAllPaginated(int pageNo) {
		Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Order.asc("id")));
		return userRepository.findAll(pageable);
	}

	@Override
	public String changePassword(Long id, PasswordRequest passwordRequest) {
		User currentUser = this.findById(id);
		if (!passwordEncoder.matches(passwordRequest.getOldPassword(), currentUser.getPassword())) {
			throw new EntityException("User " + currentUser.getUsername() + " wrong old password");
		}
		try {
			currentUser.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
			userRepository.saveAndFlush(currentUser);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EntityException("User " + currentUser.getUsername() + " change password fail");
		}
		return "User " + currentUser.getUsername() + " change password success";
	}

	@Override
	public String delete(Long id) {
		User user = this.findById(id);
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new EntityException("User " + user.getUsername() + " delete fail");
		}
		return "User " + user.getUsername() + " delete success";
	}

	@Override
	public Boolean isRightPassword(String username, String password) {
		User currentUser = this.findByUsername(username);
		return passwordEncoder.matches(password, currentUser.getPassword());
	}

	@DependsOn("initRole")
	@PostConstruct
	public void initUser() {
		if (!this.existsByUsername("admin")) {
			Set<Role> roles = new HashSet<Role>(Arrays.asList(new Role(1L, ERole.ROLE_ADMIN)));
			userRepository
					.saveAndFlush(new User(1L, "admin", passwordEncoder.encode("admin"), null, Status.ACTIVE, roles));
		}
	}
}
