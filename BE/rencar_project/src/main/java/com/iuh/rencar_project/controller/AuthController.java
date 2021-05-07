/**==============================================================================
 * System name: rencar_project
 * Version: 1.0
 * Created date: May 2, 2021 2:44:22 PM
 * Description: Created by Duy Trần Thế
 * Copyright (c) 2021 by Duy Trần Thế. All rights reserved.
===============================================================================*/
package com.iuh.rencar_project.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.RoleRequest;
import com.iuh.rencar_project.dto.request.TagRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.dto.response.PageResponse;
import com.iuh.rencar_project.dto.response.RoleResponse;
import com.iuh.rencar_project.dto.response.TagResponse;
import com.iuh.rencar_project.dto.response.UserResponse;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.service.template.IRoleService;
import com.iuh.rencar_project.service.template.ITagService;
import com.iuh.rencar_project.service.template.IUserService;
import com.iuh.rencar_project.utils.exception.bind.AccessDeniedException;
import com.iuh.rencar_project.utils.mapper.IRoleMapper;
import com.iuh.rencar_project.utils.mapper.ITagMapper;
import com.iuh.rencar_project.utils.mapper.IUserMapper;

/**
 * The Class AuthController.
 */
@RequestMapping("/api")
@RestController
public class AuthController {

	/** The role service. */
	@Autowired
	private IRoleService roleService;

	/** The user service. */
	@Autowired
	private IUserService userService;

	@Autowired
	private ITagService tagService;

	/** The role mapper. */
	@Autowired
	private IRoleMapper roleMapper;

	/** The user mapper. */
	@Autowired
	private IUserMapper userMapper;

	@Autowired
	private ITagMapper tagMapper;

	// ======================================
	// =============== ROLE =================
	// ======================================

	/**
	 * Save role.
	 *
	 * @param roleRequest the role request
	 * @return the response entity
	 */
	@PostMapping("/admin/roles")
	public ResponseEntity<?> saveRole(@RequestBody RoleRequest roleRequest) {
		return new ResponseEntity<>(new MessageResponse(roleService.save(roleRequest)), HttpStatus.OK);
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	@GetMapping("/admin/roles")
	public ResponseEntity<?> getRoles() {
		List<RoleResponse> roleResponses = roleService.findAll().stream().map(role -> {
			return roleMapper.toResponse(role);
		}).collect(Collectors.toList());
		return new ResponseEntity<>(roleResponses, HttpStatus.OK);
	}

	// ======================================
	// =============== USER =================
	// ======================================

	/**
	 * Gets the user by id or username.
	 *
	 * @param var the var
	 * @return the user by id or username
	 */
	@GetMapping("/auth/users/{var}")
	public ResponseEntity<?> getUserByIdOrUsername(@PathVariable(name = "var") String var, Principal principal) {
		UserResponse userResponse;
		try {
			userResponse = userMapper.toResponse(userService.findById(Long.valueOf(var)));
		} catch (Exception e) {
			userResponse = userMapper.toResponse(userService.findByUsername(var));
		}
		if (!principal.getName().equals(userResponse.getUsername()) && !principal.getName().equals("admin")) {
			throw new AccessDeniedException("Access Denied!");
		}
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	/**
	 * Save user.
	 *
	 * @param userRequest the user request
	 * @return the response entity
	 */
	@PostMapping("/auth/users")
	public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(new MessageResponse(userService.save(userRequest)), HttpStatus.OK);
	}

	/**
	 * Update user.
	 *
	 * @param id          the id
	 * @param userRequest the user request
	 * @return the response entity
	 */
	@PutMapping("/auth/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
			@RequestBody(required = false) Optional<UserRequest> userRequest) {
		if (userRequest.isPresent()) {
			return new ResponseEntity<>(new MessageResponse(userService.update(id, userRequest.get())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageResponse(userService.update(id)), HttpStatus.OK);
		}

	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/admin/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(new MessageResponse(userService.delete(id)), HttpStatus.OK);
	}

	/**
	 * Gets the user paginated.
	 *
	 * @param pageNo the page no
	 * @return the user paginated
	 */
	@GetMapping("/auth/users")
	public ResponseEntity<?> getUserPaginated(@RequestParam(name = "pageNo") int pageNo) {
		Page<UserResponse> pageUserResponse = userService.findAllPaginated(pageNo)
				.map(new Function<User, UserResponse>() {

					@Override
					public UserResponse apply(User user) {
						return userMapper.toResponse(user);
					}
				});
		PageResponse<UserResponse> pageResult = new PageResponse<UserResponse>(pageUserResponse.getContent(),
				pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
		return new ResponseEntity<>(pageResult, HttpStatus.OK);
	}

	@PostMapping("/auth/users/{id}/change-password")
	public ResponseEntity<?> changeUserPassword(@RequestBody PasswordRequest passwordRequest,
			@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(new MessageResponse(userService.changePassword(id, passwordRequest)),
				HttpStatus.OK);
	}

	// ======================================
	// =============== TAG ==================
	// ======================================

	@PostMapping("/auth/tags")
	public ResponseEntity<?> saveTag(@RequestBody TagRequest tagRequest) {
		return new ResponseEntity<>(new MessageResponse(tagService.save(tagRequest)), HttpStatus.OK);
	}

	@PutMapping("/auth/tags/{id}")
	public ResponseEntity<?> updateTag(@PathVariable(name = "id") Long id, @RequestBody TagRequest tagRequest) {
		return new ResponseEntity<>(new MessageResponse(tagService.update(id, tagRequest)), HttpStatus.OK);

	}

	@DeleteMapping("/auth/tags/{id}")
	public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(new MessageResponse(tagService.delete(id)), HttpStatus.OK);
	}

	@GetMapping("/auth/tags/{var}")
	public ResponseEntity<?> getTagByIdOrName(@PathVariable(name = "var") String var) {
		TagResponse tagResponse;
		try {
			tagResponse = tagMapper.toResponse(tagService.findById(Long.valueOf(var)));
		} catch (NumberFormatException e) {
			tagResponse = tagMapper.toResponse(tagService.findBySlug(var));
		}
		return new ResponseEntity<>(tagResponse, HttpStatus.OK);
	}

	@GetMapping("/auth/tags")
	public ResponseEntity<?> getTagPaginated(@RequestParam(name = "pageNo") int pageNo) {
		Page<TagResponse> pageUserResponse = tagService.findAllPaginated(pageNo).map(new Function<Tag, TagResponse>() {

			@Override
			public TagResponse apply(Tag tag) {
				return tagMapper.toResponse(tag);
			}
		});
		PageResponse<TagResponse> pageResult = new PageResponse<TagResponse>(pageUserResponse.getContent(),
				pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
		return new ResponseEntity<>(pageResult, HttpStatus.OK);
	}

	// ======================================
	// =============== TAG ==================
	// ======================================
}
