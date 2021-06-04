/*
 *
 */
package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.LoginRequest;
import com.iuh.rencar_project.dto.response.JwtResponse;
import com.iuh.rencar_project.security.JwtUtils;
import com.iuh.rencar_project.security.UserDetailsImpl;
import com.iuh.rencar_project.security.UserDetailsServiceImpl;
import com.iuh.rencar_project.utils.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Trần Thế Duy
 * @version 0.1
 * @datetime May 1, 2021 3:00:07 PM
 */

@RequestMapping("/api")
@RestController
public class PublicController {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtils jwtUtils;

    private final GenericValidator genericValidator;

    @Autowired
    public PublicController(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils, GenericValidator genericValidator) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.genericValidator = genericValidator;
    }

    @InitBinder
    public void setup(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(genericValidator);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest loginRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService
                .loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles),
                HttpStatus.OK);
    }


}
