//package com.iuh.rencar_project.dto.response;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import com.iuh.rencar_project.entity.Comment;
//import com.iuh.rencar_project.entity.Course;
//import com.iuh.rencar_project.entity.Post;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class CommentResponseDTO implements Serializable {
//
//	private static final long serialVersionUID = -5511034426808345166L;
//
//	private Long id;
//
//	private String name;
//
//	private String email;
//
//	private Date dateCreated;
//
//	private String content;
//
//	private Long like;
//
//	private Post post;
//
//	private Course course;
//
//	private Comment comment;
//
//	public CommentResponseDTO(Comment comment) {
//		this(comment.getId(), comment.getName(), comment.getEmail(), comment.getDateCreated(), comment.getContent(),
//				comment.getLike(), comment.getPost(), comment.getCourse(), comment.getComment());
//	}
//}
