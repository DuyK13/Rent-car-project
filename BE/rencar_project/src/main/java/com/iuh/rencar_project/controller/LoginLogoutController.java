/*
 * 
 */
package com.iuh.rencar_project.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iuh.rencar_project.dto.request.LoginRequest;
import com.iuh.rencar_project.dto.response.JwtResponse;
import com.iuh.rencar_project.security.JwtUtils;
import com.iuh.rencar_project.security.UserDetailsImpl;
import com.iuh.rencar_project.security.UserDetailsServiceImpl;
import com.iuh.rencar_project.utils.validator.GenericValidator;

/**
 * @author Trần Thế Duy
 * @datetime May 1, 2021 3:00:07 PM
 * @version 0.1
 */

@RequestMapping("/api")
@RestController
public class LoginLogoutController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private GenericValidator genericValidator;

	@InitBinder
	public void setup(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(genericValidator);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
		this.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
				.loadUserByUsername(loginRequest.getUsername());
		String token = jwtUtils.generateToken(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(role -> role.getAuthority())
				.collect(Collectors.toList());
		return new ResponseEntity<>(
				new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles),
				HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}
}
