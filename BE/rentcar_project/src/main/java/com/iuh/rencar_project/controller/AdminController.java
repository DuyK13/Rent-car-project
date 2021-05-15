package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.RoleRequest;
import com.iuh.rencar_project.dto.response.MessageResponse;
import com.iuh.rencar_project.dto.response.RoleResponse;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.mapper.IRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IBillService billService;

    private final IRoleMapper roleMapper;

    @Autowired
    public AdminController(IRoleService roleService, IUserService userService, ITagService tagService, ICommentService commentService, IPostService postService, ICategoryService categoryService, ICarService carService, ICourseService courseService, IBillService billService, IRoleMapper roleMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.tagService = tagService;
        this.commentService = commentService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.carService = carService;
        this.courseService = courseService;
        this.billService = billService;
        this.roleMapper = roleMapper;
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

    @PutMapping("/users/{id}")
    public ResponseEntity<?> changeUserStatus(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(userService.update(id)), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(userService.delete(id)), HttpStatus.OK);
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

    // ======================================
    // ================ CAR =================
    // ======================================
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(carService.delete(id)), HttpStatus.OK);
    }

    // ======================================
    // ============== COURSE ================
    // ======================================
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(courseService.delete(id)), HttpStatus.OK);
    }

    // ======================================
    // ============== BILL ================
    // ======================================
    @DeleteMapping("/bills/{id}")
    public ResponseEntity<?> deleteBill(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.delete(id)), HttpStatus.OK);
    }
}


