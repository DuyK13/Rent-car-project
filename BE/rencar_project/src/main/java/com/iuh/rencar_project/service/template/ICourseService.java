///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.service.template;
//
//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
//
//import com.iuh.rencar_project.dto.request.CourseRequestDTO;
//import com.iuh.rencar_project.dto.response.CourseResponseDTO;
//import com.iuh.rencar_project.entity.Course;
//
//public interface ICourseService {
//
//	CourseResponseDTO findById(Long id);
//	
//	Course findByIdAndStatusActive(Long id);
//	
//	CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);
//
//	List<CourseResponseDTO> findAllPaging(Pageable pageRequest);
//	
//	boolean deleteById(Long id);
//	
//	CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO courseRequestDTO);
//}
//
