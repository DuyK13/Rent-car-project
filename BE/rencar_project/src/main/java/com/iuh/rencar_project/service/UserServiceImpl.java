/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.service;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iuh.rencar_project.dto.request.PasswordRequestDTO;
import com.iuh.rencar_project.dto.request.UserRequestDTO;
import com.iuh.rencar_project.entity.Role;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.repository.UserRepository;
import com.iuh.rencar_project.service.template.IUserService;
import com.iuh.rencar_project.utils.enums.RolesType;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.user.UserException;
import com.iuh.rencar_project.utils.exception.bind.user.UserNotFoundException;
import com.iuh.rencar_project.utils.mapper.IUserMapper;

import lombok.extern.slf4j.Slf4j;

@PropertySource("classpath:error.properties")
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment env;

	@Autowired
	private IUserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public String save(UserRequestDTO userRequestDTO) {
		userRequestDTO.setStatus(1);
		User user = userMapper.toEntity(userRequestDTO);
		try {
			userRepository.saveAndFlush(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new UserException("User " + user.getUsername() + " save fail");
		}
		return "User " + user.getUsername() + " save success";

//		User user = userMapper.toEntity(userDTO);
//		boolean isExist = userRepository.existsByUsername(user.getUsername());
//		User currentUser = null;
//		if (user.getId() != null)
//			currentUser = this.findById(user.getId());
//		if ((user.getId() == null && !isExist) || (user.getId() != null && (currentUser.equals(user) || !isExist))) {
//			try {
//				userRepository.save(user);
//			} catch (Exception e) {
//				log.error(e.getMessage(), e);
//				return false;
//			}
//		}
//		return true;
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(env.getProperty("error.users.notFound")));
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	@Override
	public boolean existsByIdAndPassword(Long id, String password) {
		User user = this.findById(id);
		return passwordEncoder.matches(password, user.getPassword());
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public String deleteById(Long id) {
		User user = this.findById(id);
		if (!userRepository.existsById(id))
			throw new UserNotFoundException(env.getProperty("error.users.notFound"));
		userRepository.deleteById(id);
		if (userRepository.existsById(id))
			throw new UserException("User " + user.getUsername() + " delete fail");
		return "User " + user.getUsername() + " delete success";
	}

	@Override
	public String updateById(Long id) {
		String message;
		User user = this.findById(id);
		if (user.getStatus() == Status.ACTIVE) {
			user.setStatus(Status.NON_ACTIVE);
			message = "User " + user.getUsername() + " inactive";
		} else {
			user.setStatus(Status.ACTIVE);
			message = "User " + user.getUsername() + " active";
		}
		try {
			userRepository.saveAndFlush(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new UserException(env.getProperty("error.users.updateFail"));
		}
		return message;
	}

	@Override
	public String updateById(Long id, UserRequestDTO userRequestDTO) {
		User user = this.findById(id);
		String oldUsername = user.getUsername();
		userMapper.updateEntity(userRequestDTO, user);
		try {
			userRepository.saveAndFlush(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new UserException("User " + oldUsername + " update fail");
		}
		return "User " + oldUsername + " update success";
	}

	@Override
	public String updatePasswordById(Long id, PasswordRequestDTO passwordRequestDTO) {
		User user = this.findById(id);
		userMapper.updateEntity(passwordRequestDTO, user);
		try {
			userRepository.saveAndFlush(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new UserException("User " + user.getUsername() + " change password fail");
		}
		return "User " + user.getUsername() + " change password success";
	}
	
	@Override
	public Page<User> findPaginated(int page, int size) {
	
		return null;
	}

	@DependsOn(value = "initDefaultRole")
	@PostConstruct
	private void initDefaultAdmin() {
		if (!userRepository.existsByUsername("admin")) {
			userRepository.saveAndFlush(new User(null, "admin", passwordEncoder.encode("admin"), Status.ACTIVE,
					Arrays.asList(new Role(1L, RolesType.ROLE_ADMIN))));
		}
	}
}
