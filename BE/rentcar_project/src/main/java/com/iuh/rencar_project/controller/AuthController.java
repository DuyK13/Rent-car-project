/*==============================================================================
  System name: rentcar_project
  Version: 1.0
  Created date: May 2, 2021 2:44:22 PM
  Description: Created by Duy Trần Thế
  Copyright (c) 2021 by Duy Trần Thế. All rights reserved.
===============================================================================*/
package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.*;
import com.iuh.rencar_project.dto.response.*;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.exception.bind.AccessDeniedException;
import com.iuh.rencar_project.utils.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class AuthController {

    private final IRoleService roleService;

    private final IUserService userService;

    private final ITagService tagService;

    private final ICommentService commentService;

    private final IPostService postService;

    private final IRoleMapper roleMapper;

    private final IUserMapper userMapper;

    private final ITagMapper tagMapper;

    private final IPostMapper postMapper;

    private final ICommentMapper commentMapper;

    @Autowired
    public AuthController(IRoleService roleService, IUserService userService, ITagService tagService, ICommentService commentService, IPostService postService, IRoleMapper roleMapper, IUserMapper userMapper, ITagMapper tagMapper, IPostMapper postMapper, ICommentMapper commentMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.postService = postService;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
        this.tagMapper = tagMapper;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    // ======================================
    // =============== ROLE =================
    // ======================================

    @PostMapping("/admin/roles")
    public ResponseEntity<?> saveRole(@RequestBody RoleRequest roleRequest) {
        return new ResponseEntity<>(new MessageResponse(roleService.save(roleRequest)), HttpStatus.OK);
    }

    @GetMapping("/admin/roles")
    public ResponseEntity<?> getRoles() {
        List<RoleResponse> roleResponses = roleService.findAll().stream().map(roleMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(roleResponses, HttpStatus.OK);
    }

    // ======================================
    // =============== USER =================
    // ======================================

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

    @PostMapping("/auth/users")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(new MessageResponse(userService.save(userRequest)), HttpStatus.OK);
    }

    @PutMapping("/auth/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
                                        @RequestBody(required = false) UserRequest userRequest) {
        return new ResponseEntity<>(new MessageResponse(userService.update(id, userRequest)), HttpStatus.OK);

    }

    @PutMapping("/admin/users/{id}")
    public ResponseEntity<?> changeUserStatus(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(new MessageResponse(userService.update(id)), HttpStatus.OK);
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(userService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/auth/users")
    public ResponseEntity<?> getUserPaginated(@RequestParam(name = "pageNo") int pageNo) {
        Page<UserResponse> pageUserResponse = userService.findAllPaginated(pageNo)
                .map(userMapper::toResponse);
        PageResponse<UserResponse> pageResult = new PageResponse<>(pageUserResponse.getContent(),
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
    public ResponseEntity<?> getTagByIdOrSlug(@PathVariable(name = "var") String var) {
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
        Page<TagResponse> pageUserResponse = tagService.findAllPaginated(pageNo).map(tagMapper::toResponse);
        PageResponse<TagResponse> pageResult = new PageResponse<>(pageUserResponse.getContent(),
                pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // =============== POST =================
    // ======================================

    @PostMapping("/auth/posts")
    public ResponseEntity<?> savePost(@RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(new MessageResponse(postService.save(postRequest)), HttpStatus.OK);
    }

    @GetMapping("/auth/posts/{var}")
    public ResponseEntity<?> getPostByIdOrSlug(@PathVariable(name = "var") String var) {
        PostResponse postResponse;
        try {
            postResponse = postMapper.toResponse(postService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            postResponse = postMapper.toResponse(postService.findBySlug(var));
        }
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PutMapping("/auth/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable(name = "id") Long id,
                                        @RequestBody(required = false) Optional<PostRequest> postRequest) {
        return postRequest.map(request -> new ResponseEntity<>(new MessageResponse(postService.update(id, request)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new MessageResponse(postService.update(id)), HttpStatus.OK));

    }

    @DeleteMapping("/admin/postss/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(postService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/auth/posts")
    public ResponseEntity<?> getPostPaginated(@RequestParam(name = "pageNo", defaultValue = "1am") int pageNo) {
        Page<PostResponse> pagePostResponse = postService.findAllPaginated(pageNo)
                .map(postMapper::toResponse);
        PageResponse<PostResponse> pageResult = new PageResponse<>(pagePostResponse.getContent(),
                pagePostResponse.getTotalPages(), pagePostResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // =============== COMMENT ==============
    // ======================================

    @PutMapping("/auth/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.update(id)), HttpStatus.OK);

    }

    @DeleteMapping("/auth/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/auth/comments")
    public ResponseEntity<?> getCommentPaginated(@RequestParam(name = "pageNo", defaultValue = "1am") int pageNo) {
        Page<CommentResponse> pageCommentResponse = commentService.findAllPaginated(pageNo)
                .map(commentMapper::toResponse);
        PageResponse<CommentResponse> pageResult = new PageResponse<>(pageCommentResponse.getContent(),
                pageCommentResponse.getTotalPages(), pageCommentResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ============== CATEGORY ==============
    // ======================================


}
