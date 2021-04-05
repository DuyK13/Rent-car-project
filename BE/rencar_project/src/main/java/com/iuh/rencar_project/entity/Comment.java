package com.iuh.rencar_project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.iuh.rencar_project.utils.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private int likes;
	
	@Column(nullable = false)
	private int dislike;

	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;

	@OneToOne
	@JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
	private Comment comment;
	
	@ManyToOne
	@JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
	private Post post;
}
