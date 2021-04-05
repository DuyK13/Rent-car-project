///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.dto.response;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import com.iuh.rencar_project.entity.Category;
//import com.iuh.rencar_project.entity.Course;
//import com.iuh.rencar_project.entity.User;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class CourseResponseDTO implements Serializable{
//
//	private static final long serialVersionUID = -7865049955415637475L;
//
//	private Long id;
//
//	private String title;
//
//	private String shortTitle;
//
//	private Date dateCreated;
//
//	private Long view;
//
//	private String content;
//
//	private String lectureName;
//
//	private Long price;
//
//	private String link;
//
//	private User user;
//
//	private Category category;
//
//	public CourseResponseDTO(Course course) {
//		this(course.getId(), course.getTitle(), course.getShortTitle(), course.getDateCreated(), course.getView(),
//				course.getContent(), course.getLectureName(), course.getPrice(), course.getLink(), course.getUser(),
//				course.getCategory());
//	}
//}
