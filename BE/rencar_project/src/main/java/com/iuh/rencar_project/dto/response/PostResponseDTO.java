//package com.iuh.rencar_project.dto.response;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import com.iuh.rencar_project.entity.Category;
//import com.iuh.rencar_project.entity.Post;
//import com.iuh.rencar_project.entity.User;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class PostResponseDTO implements Serializable {
//
//	private static final long serialVersionUID = 5140952457577505227L;
//
//	private Long id;
//
//	private String title;
//
//	private String shortTitle;
//
//	private Date createdDate;
//
//	private Long view;
//
//	private String content;
//
//	private int status;
//
//	private User user;
//
//	private Category category;
//
//	private Post post;
//
//	public PostResponseDTO(Post p) {
//		this(p.getId(), p.getTitle(), p.getShortTitle(), p.getCreatedDate(), p.getView(), p.getContent(), p.getStatus(),
//				p.getUser(), p.getCategory(), p.getPost());
//	}
//
//}
