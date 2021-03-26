package com.iuh.rencarproject.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Comment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@Column(name = "date_created", nullable = false)
	private Date dateCreated;

	@Column(nullable = false)
	private String content;

	@Column(name = "comment_like")
	private Long like;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
	private Post post;

	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
	private Course course;

	@OneToOne(optional = false)
	@JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
	private Comment comment;
}
