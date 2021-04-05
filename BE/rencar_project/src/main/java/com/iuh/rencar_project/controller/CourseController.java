///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.iuh.rencarproject.dto.request.CourseRequestDTO;
//import com.iuh.rencarproject.dto.response.AppResponse;
//import com.iuh.rencarproject.dto.response.CourseResponseDTO;
//import com.iuh.rencarproject.dto.response.ErrorResponse;
//import com.iuh.rencarproject.dto.response.SuccessResponse;
//import com.iuh.rencarproject.entity.Course;
//import com.iuh.rencarproject.service.template.ICourseService;
//
//@RequestMapping("/courses")
//@RestController
//@PropertySource("classpath:error.properties")
//public class CourseController {
//	@Autowired
//	private Environment env;
//
//	@Autowired
//	private ICourseService courseService;
//
//	/**
//	 * Create course
//	 * 
//	 * @param courseRequestDTO
//	 * @param errors
//	 * @return
//	 */
//	@PostMapping()
//	public ResponseEntity<AppResponse> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO,
//			BindingResult errors) {
//		if (errors.hasErrors())
//			return new ResponseEntity<>(new ErrorResponse(errors.getFieldError().getDefaultMessage()), HttpStatus.OK);
//		CourseResponseDTO result = courseService.createCourse(courseRequestDTO);
//		if (result != null)
//			return new ResponseEntity<>(
//					new SuccessResponse<CourseResponseDTO>(env.getProperty("error.course.101"), result), HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.course.100")), HttpStatus.OK);
//	}
//
//	/**
//	 * Get course
//	 * 
//	 * @param courseId
//	 * @return
//	 */
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> getCourse(@PathVariable("id") Long courseId) {
//		Course course = courseService.findByIdAndStatusActive(courseId);
//		if (course != null)
//			return new ResponseEntity<>(new SuccessResponse<CourseResponseDTO>(new CourseResponseDTO(course)),
//					HttpStatus.OK);
//		else
//			return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.course.102")), HttpStatus.NOT_FOUND);
//	}
//
//	/**
//	 * Get courses by paging
//	 * 
//	 * @param page
//	 * @param pageSize
//	 * @return
//	 */
//	@GetMapping()
//	public ResponseEntity<AppResponse> getCourses(@RequestParam(value = "page", defaultValue = "0") int page,
//			@RequestParam(value = "page_size", defaultValue = "2") int pageSize) {
//		Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Order.asc("id")));
//		return new ResponseEntity<>(new SuccessResponse<List<CourseResponseDTO>>(courseService.findAllPaging(pageable)),
//				HttpStatus.OK);
//	}
//
//	@DeleteMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> deleteCourse(@PathVariable("id") Long courseId) {
//		boolean flag = courseService.deleteById(courseId);
//		if (flag)
//			return new ResponseEntity<>(new SuccessResponse<CourseResponseDTO>(env.getProperty("error.course.104")),
//					HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.course.103")), HttpStatus.NOT_FOUND);
//	}
//
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<AppResponse> updateCourse(@PathVariable("id") Long courseId,
//			@Valid @RequestBody CourseRequestDTO courseRequestDTO, BindingResult errors) {
//		if (errors.hasErrors())
//			return new ResponseEntity<>(new ErrorResponse(errors.getFieldError().getDefaultMessage()), HttpStatus.OK);
//		CourseResponseDTO course = courseService.updateCourse(courseId, courseRequestDTO);
//		if (course != null)
//			return new ResponseEntity<>(
//					new SuccessResponse<CourseResponseDTO>(env.getProperty("error.course.106"), course), HttpStatus.OK);
//		return new ResponseEntity<>(new ErrorResponse(env.getProperty("error.course.105")), HttpStatus.OK);
//	}
//}
