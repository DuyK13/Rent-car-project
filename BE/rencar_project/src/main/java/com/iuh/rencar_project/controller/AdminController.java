/**
 * @author trant
 * @created_date Apr 18, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.rencar_project.dto.RoleDTO;
import com.iuh.rencar_project.dto.request.PasswordRequestDTO;
import com.iuh.rencar_project.dto.request.UserRequestDTO;
import com.iuh.rencar_project.dto.response.UserResponseDTO;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.service.template.IUserService;
import com.iuh.rencar_project.utils.exception.bind.role.RoleException;
import com.iuh.rencar_project.utils.mapper.IRoleMapper;
import com.iuh.rencar_project.utils.mapper.IUserMapper;
import com.iuh.rencar_project.utils.validator.GenericValidator;

@RequestMapping("/v1/auth")
@PropertySource("classpath:error.properties")
@RestController
public class AdminController {

	private final HttpStatus OK = HttpStatus.OK;

	@Autowired
	private Environment env;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserMapper userMapper;

	@Autowired
	private IRoleMapper roleMapper;

	@Autowired
	private GenericValidator genericValidator;

	@InitBinder
	public void setup(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(genericValidator);
	}

	// Roles
	@PostMapping(value = "/roles")
	public ResponseEntity<Object> saveRole(@RequestBody RoleDTO roleDTO) {
		boolean isSuccess = roleService.saveAndUpdate(roleDTO);
		if (isSuccess)
			return new ResponseEntity<>(OK);
		throw new RoleException(env.getProperty("error.roles.save"));
	}

	@GetMapping(value = "/roles")
	public ResponseEntity<Object> getRoles() {
		List<RoleDTO> roles = roleMapper.toListDTO(roleService.findAll());
		return new ResponseEntity<>(roles, OK);

	}

	@GetMapping(value = "/roles/{id}")
	public ResponseEntity<Object> getRole(@PathVariable(name = "id") Long id) {
		RoleDTO role = roleMapper.toDTO(roleService.findById(id));
		return new ResponseEntity<>(role, OK);
	}

	// Users
	@PostMapping(value = "/users")
	public ResponseEntity<Object> saveUser(@Validated @RequestBody UserRequestDTO userRequestDTO) {
		return new ResponseEntity<>(userService.save(userRequestDTO), OK);
	}

	@GetMapping(value = "/users")
	public ResponseEntity<Object> getUsers(
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		List<UserResponseDTO> users = userMapper.toListDTO(userService.findAll());
		return new ResponseEntity<>(users, OK);

	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable(name = "id") Long id) {
		UserResponseDTO user = userMapper.toDTO(userService.findById(id));
		return new ResponseEntity<>(user, OK);
	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<Object>(userService.deleteById(id), OK);
	}

	@PutMapping(value = "/users/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable(name = "id") Long id,
			@Validated @RequestBody(required = false) UserRequestDTO userRequestDTO) {
		System.out.println("1");
		if (userRequestDTO != null) {
			return new ResponseEntity<Object>(userService.updateById(id, userRequestDTO), OK);
		} else {
			return new ResponseEntity<Object>(userService.updateById(id), OK);
		}
	}

	@PutMapping(value = "/users/{id}/change-password")
	public ResponseEntity<Object> updateUserPassword(@PathVariable(name = "id") Long id,
			@Validated @RequestBody PasswordRequestDTO passwordRequestDTO) {
		return new ResponseEntity<Object>(userService.updatePasswordById(id, passwordRequestDTO), OK);
	}

	// Login
}
