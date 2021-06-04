package com.iuh.rencar_project.controller;

import com.iuh.rencar_project.dto.request.*;
import com.iuh.rencar_project.dto.response.*;
import com.iuh.rencar_project.service.template.*;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequestMapping("/api/moderator")
@RestController
public class ModeratorController {

    private final ITagService tagService;

    private final ICommentService commentService;

    private final IPostService postService;

    private final ICategoryService categoryService;

    private final ICarService carService;

    private final ICourseService courseService;

    private final IBillService billService;

    private final ITagMapper tagMapper;

    private final IPostMapper postMapper;

    private final ICategoryMapper categoryMapper;

    private final ICarMapper carMapper;

    private final ICourseMapper courseMapper;

    private final IBillMapper billMapper;

    private final ICommentMapper commentMapper;

    @Autowired
    public ModeratorController(ITagService tagService, ICommentService commentService, IPostService postService, ICategoryService categoryService, ICarService carService, ICourseService courseService, IBillService billService, ITagMapper tagMapper, IPostMapper postMapper, ICategoryMapper categoryMapper, ICarMapper carMapper, ICourseMapper courseMapper, IBillMapper billMapper, ICommentMapper commentMapper) {
        this.tagService = tagService;
        this.commentService = commentService;
        this.postService = postService;
        this.categoryService = categoryService;
        this.carService = carService;
        this.courseService = courseService;
        this.billService = billService;
        this.tagMapper = tagMapper;
        this.postMapper = postMapper;
        this.categoryMapper = categoryMapper;
        this.carMapper = carMapper;
        this.courseMapper = courseMapper;
        this.billMapper = billMapper;
        this.commentMapper = commentMapper;
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

    @GetMapping("/tags/{id}")
    public ResponseEntity<?> getTagById(@PathVariable(name = "id") Long id) {
        TagResponse tagResponse = tagMapper.toResponse(tagService.findById(id));
        return new ResponseEntity<>(tagResponse, HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getTagPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "search", required = false) Optional<String> search) {
        Page<TagResponse> pageUserResponse = search.map(s -> tagService.search(pageNo, pageSize, s).map(tagMapper::toResponse)).orElseGet(() -> tagService.findAllPaginated(pageNo, pageSize).map(tagMapper::toResponse));
        PageResponse<TagResponse> pageResult = new PageResponse<>(pageUserResponse.getContent(),
                pageUserResponse.getTotalPages(), pageUserResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // =============== POST =================
    // ======================================

    @PostMapping("/posts")
    public ResponseEntity<?> savePost(@RequestPart(name = "post") PostRequest postRequest, @RequestPart("file") MultipartFile multipartFile) {
        return new ResponseEntity<>(new MessageResponse(postService.save(postRequest, multipartFile)), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(name = "id") Long id) {
        PostResponse postResponse = postMapper.toResponse(postService.findById(id));
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@PathVariable(name = "id") Long id,
                                        @RequestPart(name = "post") PostRequest postRequest, @RequestPart(name = "file") Optional<MultipartFile> multipartFile) {
        return multipartFile.map(file -> new ResponseEntity<>(new MessageResponse(postService.update(id, postRequest, file)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new MessageResponse(postService.update(id, postRequest)), HttpStatus.OK));

    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPostPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "search", required = false) Optional<String> search) {
        Page<PostResponse> pagePostResponse = search.map(s -> postService.search(pageNo, pageSize, s)
                .map(postMapper::toResponse)).orElseGet(() -> postService.findAllPaginated(pageNo, pageSize)
                .map(postMapper::toResponse));
        PageResponse<PostResponse> pageResult = new PageResponse<>(pagePostResponse.getContent(),
                pagePostResponse.getTotalPages(), pagePostResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // =============== COMMENT ==============
    // ======================================

    @PutMapping("/comments/{id}")
    public ResponseEntity<?> setCommentAvailability(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.setAvailability(id)), HttpStatus.OK);

    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(new MessageResponse(commentService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<?> getCommentPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "status", required = false) Optional<String> status, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        Page<CommentResponse> pageCommentResponse = null;
        if (status.isPresent()) {
            if (status.get().equalsIgnoreCase(Status.DISABLE.name()))
                pageCommentResponse = commentService.findAllPaginatedDisable(pageNo, pageSize)
                        .map(commentMapper::toResponse);
            else if (status.get().equalsIgnoreCase(Status.ENABLE.name()))
                pageCommentResponse = commentService.findAllPaginatedEnable(pageNo, pageSize)
                        .map(commentMapper::toResponse);
        } else {
            pageCommentResponse = commentService.findAllPaginated(pageNo, pageSize)
                    .map(commentMapper::toResponse);
        }
        PageResponse<CommentResponse> pageResult = null;
        if (pageCommentResponse != null)
            pageResult = new PageResponse<>(pageCommentResponse.getContent(),
                    pageCommentResponse.getTotalPages(), pageCommentResponse.getNumber());
        return new ResponseEntity<>(pageResult != null ? pageResult : new MessageResponse("Nothing found"), HttpStatus.OK);
    }


    // ======================================
    // ============== CATEGORY ==============
    // ======================================

    @PostMapping("/categories")
    public ResponseEntity<?> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(new MessageResponse(categoryService.save(categoryRequest)), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(name = "id") Long id) {
        CategoryResponse categoryResponse = categoryMapper.toResponse(categoryService.findById(id));
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id,
                                            @RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(new MessageResponse(categoryService.update(id, categoryRequest)), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getCategoryPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name
            = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "search", required = false) Optional<String> search) {
        Page<CategoryResponse> pageCategoryResponse = search.map(s -> categoryService.search(pageNo, pageSize, s)
                .map(categoryMapper::toResponse)).orElseGet(() -> categoryService.findAllPaginated(pageNo, pageSize)
                .map(categoryMapper::toResponse));
        PageResponse<CategoryResponse> pageResult = new PageResponse<>(pageCategoryResponse.getContent(),
                pageCategoryResponse.getTotalPages(), pageCategoryResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ================= CAR ================
    // ======================================

    @PostMapping("/cars")
    public ResponseEntity<?> saveCar(@RequestPart(name = "car") CarRequest carRequest, @RequestPart(name = "file") MultipartFile multipartFile) {
        return new ResponseEntity<>(new MessageResponse(carService.save(carRequest, multipartFile)), HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<?> getCarById(@PathVariable(name = "id") Long id) {
        CarResponse carResponse = carMapper.toResponse(carService.findById(id));
        return new ResponseEntity<>(carResponse, HttpStatus.OK);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<?> updateCar(@PathVariable(name = "id") Long id,
                                       @RequestPart(name = "car") CarRequest carRequest,
                                       @RequestPart(name = "file", required = false) Optional<MultipartFile> multipartFile) {
        return multipartFile.map(file -> new ResponseEntity<>(new MessageResponse(carService.update(id, carRequest, file)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new MessageResponse(carService.update(id, carRequest)), HttpStatus.OK));
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getCarPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "search", required = false) Optional<String> search) {
        Page<CarResponse> pageCarResponse = search.map(s -> carService.search(pageNo, pageSize, s)
                .map(carMapper::toResponse)).orElseGet(() -> carService.findAllPaginated(pageNo, pageSize)
                .map(carMapper::toResponse));
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

    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable(name = "id") Long id) {
        CourseResponse courseResponse = courseMapper.toResponse(courseService.findById(id));
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable(name = "id") Long id,
                                          @RequestBody CourseRequest courseRequest) {
        return new ResponseEntity<>(new MessageResponse(courseService.update(id, courseRequest)), HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getCoursePaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
        Page<CourseResponse> pageCourseResponse = courseService.findAllPaginated(pageNo, pageSize)
                .map(courseMapper::toResponse);
        PageResponse<CourseResponse> pageResult = new PageResponse<>(pageCourseResponse.getContent(),
                pageCourseResponse.getTotalPages(), pageCourseResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    // ======================================
    // ================ BILL ================
    // ======================================

    @GetMapping("/bills/{id}")
    public ResponseEntity<?> getBillById(@PathVariable(name = "id") Long id) {
        BillResponse billResponse = billMapper.toResponse(billService.findById(id));
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @GetMapping("/bills")
    public ResponseEntity<?> getBillPaginated(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo, @RequestParam(name = "pageSize", defaultValue = "5") int pageSize, @RequestParam(name = "search", required = false) Optional<String> search) {
        Page<BillResponse> pageBillResponse = search.map(s -> billService.search(pageNo, pageSize, s)
                .map(billMapper::toResponse)).orElseGet(() -> billService.findAllPaginated(pageNo, pageSize)
                .map(billMapper::toResponse));
        PageResponse<BillResponse> pageResult = new PageResponse<>(pageBillResponse.getContent(),
                pageBillResponse.getTotalPages(), pageBillResponse.getNumber());
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }
}
