///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.iuh.rencar_project.dto.request.CourseRequestDTO;
//import com.iuh.rencar_project.dto.response.CourseResponseDTO;
//import com.iuh.rencar_project.entity.Category;
//import com.iuh.rencar_project.entity.Course;
//import com.iuh.rencar_project.entity.User;
//import com.iuh.rencar_project.repository.CategoryRepository;
//import com.iuh.rencar_project.repository.CourseRepository;
//import com.iuh.rencar_project.repository.UserRepository;
//import com.iuh.rencar_project.service.template.ICourseService;
//import com.iuh.rencar_project.utils.DeleteStatus;
//
//@Service
//public class CourseServiceImpl implements ICourseService {
//
//	final static Logger logger = Logger.getLogger(CourseServiceImpl.class);
//
//	@Autowired
//	CourseRepository courseRepository;
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	CategoryRepository categoryRepository;
//
//	/**
//	 * ADMIN & MODERATOR
//	 */
//	@Override
//	public CourseResponseDTO findById(Long id) {
//		CourseResponseDTO result = null;
//		try {
//			Course course = courseRepository.findById(id).get();
//			result = new CourseResponseDTO(course);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	/**
//	 * User
//	 */
//	@Override
//	public Course findByIdAndStatusActive(Long id) {
//		Course result = null;
//		try {
//			result = courseRepository.findByIdAndStatusEquals(id, DeleteStatus.ACTIVE.ordinal());
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//	
//
//	@Override
//	public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
//		CourseResponseDTO result = null;
//		try {
//			User user = userRepository.findById(courseRequestDTO.getUserId()).get();
//			Category category = categoryRepository.findById(courseRequestDTO.getCategoryId()).get();
//			Course course = courseRequestDTO.convertCourseCreateToEntity(user, category);
//			course = courseRepository.saveAndFlush(course);
//			result = new CourseResponseDTO(course);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public List<CourseResponseDTO> findAllPaging(Pageable pageRequest) {
//		List<CourseResponseDTO> result = null;
//		try {
//			Page<Course> courses = courseRepository.findAll(pageRequest);
//			result = courses.getContent().stream().map(x -> {
//				return new CourseResponseDTO(x);
//			}).collect(Collectors.toList());
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//	@Override
//	public boolean deleteById(Long id) {
//		try {
//			// Tìm course mà status active
//			Course course = courseRepository.findByIdAndStatusEquals(id, DeleteStatus.ACTIVE.ordinal());
//			course.setStatus(DeleteStatus.NON_ACTIVE.ordinal());
//			course = courseRepository.saveAndFlush(course);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return courseRepository.existsByIdAndStatusEquals(id, DeleteStatus.NON_ACTIVE.ordinal());
//	}
//
//	@Override
//	public CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO courseRequestDTO) {
//		CourseResponseDTO result = null;
//		try {
//			Course course = courseRepository.findById(courseId).get();
//			course = courseRequestDTO.convertCourseUpdateToEntity(course, categoryRepository.findById(courseId).get());
//			course = courseRepository.saveAndFlush(course);
//			result = new CourseResponseDTO(course);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//		return result;
//	}
//
//
//
//
//}