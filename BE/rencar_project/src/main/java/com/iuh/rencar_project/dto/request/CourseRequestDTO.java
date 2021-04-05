///**
// * @author DuyTT10
// * @date Mar 26, 2021
// * @version 1.0
// */
//package com.iuh.rencar_project.dto.request;
//
//import java.util.Date;
//
//import com.iuh.rencar_project.entity.Category;
//import com.iuh.rencar_project.entity.Course;
//import com.iuh.rencar_project.entity.User;
//import com.iuh.rencar_project.utils.DeleteStatus;
//
//import lombok.Data;
//
//@Data
//public class CourseRequestDTO {
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
//	private int status;
//
//	private Long userId;
//
//	private Long categoryId;
//	
//	public Course convertCourseCreateToEntity(User user, Category category) {
//		Course course = new Course();
//		course.setTitle(title);
//		course.setShortTitle(shortTitle);
//		course.setDateCreated(dateCreated);
//		course.setView(0L);
//		course.setContent(content);
//		course.setLectureName(lectureName);
//		course.setPrice(price);
//		course.setLink(link);
//		course.setStatus(DeleteStatus.ACTIVE.ordinal());
//		course.setUser(user);
//		course.setCategory(category);
//		return course;
//	}
//	
//	public Course convertCourseUpdateToEntity(Course course, Category category) {
//		course.setTitle(title);
//		course.setShortTitle(shortTitle);
//		course.setContent(content);
//		course.setLectureName(lectureName);
//		course.setPrice(price);
//		course.setLink(link);
//		course.setStatus(status);
//		course.setCategory(category);
//		return course;
//	}
//}
