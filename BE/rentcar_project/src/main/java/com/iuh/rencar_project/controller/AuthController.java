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
import java.util.Optional;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

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

    private final IUserMapper userMapper;

    private final ITagMapper tagMapper;

    private final IPostMapper postMapper;

    private final ICommentMapper commentMapper;

    private final ICategoryMapper categoryMapper;

    private final ICarMapper carMapper;

    private final ICourseMapper courseMapper;

    private final IBillMapper billMapper;

    @Autowired
    public AuthController(IRoleService roleService, IUserService userService, ITagService tagService, ICommentService commentService, IPostService postService, ICategoryService categoryService, ICarService carService, ICourseService courseService, IBillService billService, IRoleMapper roleMapper, IUserMapper userMapper, ITagMapper tagMapper, IPostMapper postMapper, ICommentMapper commentMapper, ICategoryMapper categoryMapper, ICarMapper carMapper, ICourseMapper courseMapper, IBillMapper billMapper) {
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
        this.userMapper = userMapper;
        this.tagMapper = tagMapper;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.categoryMapper = categoryMapper;
        this.carMapper = carMapper;
        this.courseMapper = courseMapper;
        this.billMapper = billMapper;
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
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
                                        @RequestBody(required = false) UserRequest userRequest) {
        return new ResponseEntity<>(new MessageResponse(userService.update(id, userRequest)), HttpStatus.OK);

    }

    @GetMapping("/users")
    public ResponseEntity<?> getUserPaginated(@RequestParam(name = "pageNo") int pageNo) {
        Page<UserResponse> pageUserResponse = userService.findAllPaginated(pageNo)
                .map(userMapper::toResponse);
        PageResponse<UserResponse> pageResult = new PageResponse<>(pageUserResponse.getContent(),
                pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/change-password")
    public ResponseEntity<?> changeUserPassword(@RequestBody PasswordRequest passwordRequest,
                                                @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(userService.changePassword(id, passwordRequest)),
                HttpStatus.OK);
    }

    // ======================================
    // =============== TAG ==================
    // ======================================

    @PostMapping("/tags")
    public ResponseEntity<?> saveTag(@RequestBody TagRequest tagRequest) {
        return new ResponseEntity<>(new MessageResponse(tagService.save(tagRequest)), HttpStatus.OK);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<?> updateTag(@PathVariable(name = "id") Long id, @RequestBody TagRequest tagRequest) {
        return new ResponseEntity<>(new MessageResponse(tagService.update(id, tagRequest)), HttpStatus.OK);

    }

    @GetMapping("/tags/{var}")
    public ResponseEntity<?> getTagByIdOrSlug(@PathVariable(name = "var") String var) {
        TagResponse tagResponse;
        try {
            tagResponse = tagMapper.toResponse(tagService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            tagResponse = tagMapper.toResponse(tagService.findBySlug(var));
            System.out.println(tagService.findBySlug(var).getCreatedDate());
            System.out.println(tagResponse.getCreatedDate());
        }
        return new ResponseEntity<>(tagResponse, HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getTagPaginated(@RequestParam(name = "pageNo") int pageNo) {
        Page<TagResponse> pageUserResponse = tagService.findAllPaginated(pageNo).map(tagMapper::toResponse);
        PageResponse<TagResponse> pageResult = new PageResponse<>(pageUserResponse.getContent(),
                pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // =============== POST =================
    // ======================================

    @PostMapping("/posts")
    public ResponseEntity<?> savePost(@RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(new MessageResponse(postService.save(postRequest)), HttpStatus.OK);
    }

    @GetMapping("/posts/{var}")
    public ResponseEntity<?> getPostByIdOrSlug(@PathVariable(name = "var") String var) {
        PostResponse postResponse;
        try {
            postResponse = postMapper.toResponse(postService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            postResponse = postMapper.toResponse(postService.findBySlug(var));
        }
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable(name = "id") Long id,
                                        @RequestBody(required = false) Optional<PostRequest> postRequest) {
        return postRequest.map(request -> new ResponseEntity<>(new MessageResponse(postService.update(id, request)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new MessageResponse(postService.update(id)), HttpStatus.OK));

    }

    @GetMapping("/posts")
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

    @PutMapping("/comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.update(id)), HttpStatus.OK);

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/comments")
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

    @PostMapping("/categories")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(new MessageResponse(categoryService.save(categoryRequest)), HttpStatus.OK);
    }

    @GetMapping("/categories/{var}")
    public ResponseEntity<?> getCategoryByIdOrSlug(@PathVariable(name = "var") String var) {
        CategoryResponse categoryResponse;
        try {
            categoryResponse = categoryMapper.toResponse(categoryService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            categoryResponse = categoryMapper.toResponse(categoryService.findBySlug(var));
        }
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id,
                                            @RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(new MessageResponse(categoryService.update(id, categoryRequest)), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategoryPaginated(@RequestParam(name = "pageNo", defaultValue = "1am") int pageNo) {
        Page<CategoryResponse> pageCategoryResponse = categoryService.findAllPaginated(pageNo)
                .map(categoryMapper::toResponse);
        PageResponse<CategoryResponse> pageResult = new PageResponse<>(pageCategoryResponse.getContent(),
                pageCategoryResponse.getTotalPages(), pageCategoryResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ================= CAR ================
    // ======================================

    @PostMapping("/cars")
    public ResponseEntity<?> saveCar(@RequestBody CarRequest carRequest) {
        return new ResponseEntity<>(new MessageResponse(carService.save(carRequest)), HttpStatus.OK);
    }

    @GetMapping("/cars/{var}")
    public ResponseEntity<?> getCarByIdOrSlug(@PathVariable(name = "var") String var) {
        CarResponse carResponse;
        try {
            carResponse = carMapper.toResponse(carService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            carResponse = carMapper.toResponse(carService.findBySlug(var));
        }
        return new ResponseEntity<>(carResponse, HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(@PathVariable(name = "id") Long id,
                                       @RequestBody CarRequest carRequest) {
        return new ResponseEntity<>(new MessageResponse(carService.update(id, carRequest)), HttpStatus.OK);
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getCarPaginated(@RequestParam(name = "pageNo", defaultValue = "1am") int pageNo) {
        Page<CarResponse> pageCarResponse = carService.findAllPaginated(pageNo)
                .map(carMapper::toResponse);
        PageResponse<CarResponse> pageResult = new PageResponse<>(pageCarResponse.getContent(),
                pageCarResponse.getTotalPages(), pageCarResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ============== COURSE ================
    // ======================================

    @PostMapping("/courses")
    public ResponseEntity<?> saveCourse(@RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(new MessageResponse(courseService.save(courseRequest)), HttpStatus.OK);
    }

    @GetMapping("/courses/{var}")
    public ResponseEntity<?> getCourseByIdOrSlug(@PathVariable(name = "var") String var) {
        CourseResponse courseResponse;
        try {
            courseResponse = courseMapper.toResponse(courseService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            courseResponse = courseMapper.toResponse(courseService.findBySlug(var));
        }
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable(name = "id") Long id,
                                          @RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(new MessageResponse(courseService.update(id, courseRequest)), HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getCoursePaginated(@RequestParam(name = "pageNo", defaultValue = "1am") int pageNo) {
        Page<CourseResponse> pageCourseResponse = courseService.findAllPaginated(pageNo)
                .map(courseMapper::toResponse);
        PageResponse<CourseResponse> pageResult = new PageResponse<>(pageCourseResponse.getContent(),
                pageCourseResponse.getTotalPages(), pageCourseResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ================ BILL ================
    // ======================================

    @PostMapping("/bills")
    public ResponseEntity<?> saveBill(@RequestBody BillRequest billRequest) {
        return new ResponseEntity<>(new MessageResponse(billService.save(billRequest)), HttpStatus.OK);
    }

    @GetMapping("/bills/{var}")
    public ResponseEntity<?> getBillByIdOrSlug(@PathVariable(name = "var") String var) {
        BillResponse billResponse;
        try {
            billResponse = billMapper.toResponse(billService.findById(Long.valueOf(var)));
        } catch (NumberFormatException e) {
            billResponse = billMapper.toResponse(billService.findBySlug(var));
        }
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @PutMapping("/bills/{id}")
    public ResponseEntity<?> updateBill(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(billService.update(id)), HttpStatus.OK);
    }

    @GetMapping("/bills")
    public ResponseEntity<?> getBillPaginated(@RequestParam(name = "pageNo", defaultValue = "1am") int pageNo) {
        Page<BillResponse> pageBillResponse = billService.findAllPaginated(pageNo)
                .map(billMapper::toResponse);
        PageResponse<BillResponse> pageResult = new PageResponse<>(pageBillResponse.getContent(),
                pageBillResponse.getTotalPages(), pageBillResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
}
