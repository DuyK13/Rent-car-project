package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.dto.request.CommentRequest;
import com.iuh.rencar_project.dto.response.*;
import com.iuh.rencar_project.entity.Tag;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.mapper.*;
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
 * @date 5/22/2021 6:38 PM
 */
@RestController
@RequestMapping("/api/guest")
public class GuestController {

    private final IPostService postService;

    private final ICourseService courseService;

    private final ICommentService commentService;

    private final ICategoryService categoryService;

    private final ITagService tagService;

    private final ICarService carService;

    private final IBillService billService;

    private final IPostMapper postMapper;

    private final ICourseMapper courseMapper;

    private final ICommentMapper commentMapper;

    private final ICategoryMapper categoryMapper;

    private final ICarMapper carMapper;

    private final IBillMapper billMapper;

    private final ITagMapper tagMapper;

    @Autowired
    public GuestController(IPostService postService, ICourseService courseService, ICommentService commentService, ICategoryService categoryService, ITagService tagService, ICarService carService, IBillService billService, IPostMapper postMapper, ICourseMapper courseMapper, ICommentMapper commentMapper, ICategoryMapper categoryMapper, ICarMapper carMapper, IBillMapper billMapper, ITagMapper tagMapper) {
        this.postService = postService;
        this.courseService = courseService;
        this.commentService = commentService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.carService = carService;
        this.billService = billService;
        this.postMapper = postMapper;
        this.courseMapper = courseMapper;
        this.commentMapper = commentMapper;
        this.categoryMapper = categoryMapper;
        this.carMapper = carMapper;
        this.billMapper = billMapper;
        this.tagMapper = tagMapper;
    }

    // ======================================
    // =============== Tag ==================
    // ======================================

    @GetMapping("/tags")
    public ResponseEntity<?> getListTag() {
        List<TagResponse> tagResponses = tagService.findAll().stream().map(tagMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(tagResponses, HttpStatus.OK);
    }

    // ======================================
    // =============== POST =================
    // ======================================

    @GetMapping("/tags/{tagSlug}/posts")
    public ResponseEntity<?> getPagePostByTagSLug(@PathVariable(name = "tagSlug") String tagSlug, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        Tag tag = tagService.findBySlug(tagSlug);
        Page<PostResponse> postResponsePage = postService.findAllPaginatedByTagForGuest(tag, pageNo).map(postMapper::toResponse);
        PageResponse<PostResponse> pageResult = new PageResponse<>(postResponsePage.getContent(), postResponsePage.getTotalPages(), postResponsePage.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPagePost(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        Page<PostResponse> postResponsePage = postService.findAllPaginatedForGuest(pageNo).map(postMapper::toResponse);
        PageResponse<PostResponse> pageResult = new PageResponse<>(postResponsePage.getContent(), postResponsePage.getTotalPages(), postResponsePage.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @GetMapping("/posts/{slug}")
    public ResponseEntity<?> getPostBySlug(@PathVariable(name = "slug") String slug) {
        PostResponse postResponse = postMapper.toResponse(postService.findBySlugForGuest(slug));
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // ======================================
    // =============== Course ===============
    // ======================================

    @GetMapping("/courses")
    public ResponseEntity<?> getListCourses() {
        List<CourseResponse> courseResponseList = courseService.findAll().stream().map(courseMapper::toResponse).collect(Collectors.toList());
        return new ResponseEntity<>(courseResponseList, HttpStatus.OK);
    }

    @GetMapping("/courses/{slug}")
    public ResponseEntity<?> getCourseBySlug(@PathVariable(name = "slug") String slug) {
        CourseResponse courseResponse = courseMapper.toResponse(courseService.findBySlug(slug));
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    // ======================================
    // =============== Comment ==============
    // ======================================

    @PostMapping("/posts/{slug}/comments")
    public ResponseEntity<?> saveComment(@RequestBody CommentRequest commentRequest, @PathVariable(name = "slug") String slug) {
        String message = commentService.save(slug, commentRequest);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

//    @GetMapping("/posts/{slug}/comments")
//    public ResponseEntity<?> getCommentsByPost(@PathVariable(name = "slug") String slug, @RequestParam(name = "limit", defaultValue = "5") int limit){
//        List<CommentResponse> commentResponseList = postService.findCommentsBySlug(slug).stream().map(commentMapper::toResponse).collect(Collectors.toList());
////        List<CommentResponse> commentResponseList = postService.findCommentsBySlugAndCommentsCommentIdAndCommentsCommentStatus(slug, null, Status.ACTIVE).stream().map(commentMapper::toResponse).collect(Collectors.toList());
//        return new ResponseEntity<>(commentResponseList, HttpStatus.OK);
//    }

    // ======================================
    // =============== Category =============
    // ======================================

    @GetMapping("/categories")
    public ResponseEntity<?> getPageCategories(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        Page<CategoryResponse> categoryResponsePage = categoryService.findAllPaginatedForGuest(pageNo).map(categoryMapper::toResponse);
        PageResponse<CategoryResponse> pageResult = new PageResponse<>(categoryResponsePage.getContent(), categoryResponsePage.getTotalPages(), categoryResponsePage.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    @GetMapping("/categories/{slug}")
    public ResponseEntity<?> getCars(@PathVariable(name = "slug") String slug) {
        CategoryResponse categoryResponse = categoryMapper.toResponse(categoryService.findBySlugForGuest(slug));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    // ======================================
    // ================ Car =================
    // ======================================

    @GetMapping("cars/{slug}")
    public ResponseEntity<?> getCar(@PathVariable(name = "slug") String slug) {
        CarResponse carResponse = carMapper.toResponse(carService.findBySlugForGuest(slug));
        return new ResponseEntity<>(carResponse, HttpStatus.OK);
    }

    // ======================================
    // ================= Bill ===============
    // ======================================

    @PostMapping("/bills")
    public ResponseEntity<?> registerBill(@RequestBody BillRequest billRequest) {
        String message = billService.save(billRequest);
        return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
    }

    @GetMapping("/bills/{slug}")
    public ResponseEntity<?> getBillBySlug(@PathVariable(name = "slug") String slug) {
        BillResponse billResponse = billMapper.toResponse(billService.findBySlug(slug));
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }
}
