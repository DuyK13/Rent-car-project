package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.PasswordRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.dto.response.UserResponse;
import com.iuh.rencar_project.entity.User;
import com.iuh.rencar_project.service.template.IUserService;
import com.iuh.rencar_project.utils.exception.bind.AccessDeniedException;
import com.iuh.rencar_project.utils.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/25/2021 1:42 AM
 */
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final IUserService userService;
    private final IUserMapper userMapper;

    @Autowired
    public AuthController(IUserService userService, IUserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // ======================================
    // =============== USER =================
    // ======================================

    @GetMapping("/users/{var}")
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

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserEmail(@PathVariable(name = "id") Long id,
                                             @RequestBody(required = false) UserRequest userRequest, Principal principal) {
        User user = userService.findById(id);
        if (!principal.getName().equals(user.getUsername())) {
            throw new AccessDeniedException("Access Denied!");
        }
        return new ResponseEntity<>(new MessageResponse(userService.update(id, userRequest)), HttpStatus.OK);

    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<?> changeUserPassword(@RequestBody PasswordRequest passwordRequest,
                                                @PathVariable(name = "id") Long id, Principal principal) {
        User user = userService.findById(id);
        if (!principal.getName().equals(user.getUsername())) {
            throw new AccessDeniedException("Access Denied!");
        }
        return new ResponseEntity<>(new MessageResponse(userService.changePassword(id, passwordRequest)),
                HttpStatus.OK);
    }
}
