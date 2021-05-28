package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.RoleRequest;
import com.iuh.rencar_project.dto.request.UserRequest;
import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.dto.response.PageResponse;
import com.iuh.rencar_project.dto.response.RoleResponse;
import com.iuh.rencar_project.dto.response.UserResponse;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.mapper.IRoleMapper;
import com.iuh.rencar_project.utils.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 6:05 PM
 */
@RequestMapping("/api/admin")
@RestController
public class AdminController {

    private final IRoleService roleService;

    private final IUserService userService;

    private final ITagService tagService;

    private final ICommentService commentService;

    private final IPostService postService;

    private final ICategoryService categoryService;

    private final ICarService carService;

    private final ICourseService courseService;

    private final IRoleMapper roleMapper;

    private final IUserMapper userMapper;

    @Autowired
    public AdminController(IRoleService roleService, IUserService userService, ITagService tagService, ICommentService commentService, IPostService postService, ICategoryService categoryService, ICarService carService, ICourseService courseService, IRoleMapper roleMapper, IUserMapper userMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.carService = carService;
        this.courseService = courseService;
        this.roleMapper = roleMapper;
        this.userMapper = userMapper;
    }

    // ======================================
    // =============== ROLE =================
    // ======================================

    @PostMapping("/roles")
    public ResponseEntity<?> saveRole(@RequestBody RoleRequest roleRequest) {
        return new ResponseEntity<>(new MessageResponse(roleService.save(roleRequest)), HttpStatus.OK);
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        List<RoleResponse> roleResponses = roleService.findAll().stream().map(roleMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(roleResponses, HttpStatus.OK);
    }

    // ======================================
    // =============== USER =================
    // ======================================

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(new MessageResponse(userService.save(userRequest)), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> changeUserStatus(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(userService.update(id)), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(userService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        Page<UserResponse> pageUserResponse = userService.findAllPaginated(pageNo)
                .map(userMapper::toResponse);
        PageResponse<UserResponse> pageResult = new PageResponse<>(pageUserResponse.getContent(),
                pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ================ TAG =================
    // ======================================

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(tagService.delete(id)), HttpStatus.OK);
    }

    // ======================================
    // ================ POST ================
    // ======================================

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(postService.delete(id)), HttpStatus.OK);
    }

    // ======================================
    // ============= CATEGORY ===============
    // ======================================
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(categoryService.delete(id)), HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> changeCategoryStatus(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(categoryService.update(id)), HttpStatus.OK);
    }

    // ======================================
    // ================ CAR =================
    // ======================================
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(carService.delete(id)), HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> changeCarStatus(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(carService.changeStatus(id)), HttpStatus.OK);
    }

    // ======================================
    // ============== COURSE ================
    // ======================================

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> changeCourseStatus(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(courseService.update(id)), HttpStatus.OK);
    }

    // ======================================
    // ============== COMMENT ===============
    // ======================================
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.delete(id)), HttpStatus.OK);
    }
}


