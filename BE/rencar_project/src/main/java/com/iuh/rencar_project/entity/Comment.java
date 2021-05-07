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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.iuh.rencar_project.utils.enums.Status;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String email;

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(nullable = false)
	private String content;

	private int likes;

	private int dislike;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Status status;

	@OneToOne
	@JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
	private Comment comment;

	public Comment(Long id, String name, String email, Date createdDate, String content, int likes, int dislike,
			Status status, Comment comment) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdDate = createdDate;
		this.content = content;
		this.likes = likes;
		this.dislike = dislike;
		this.status = status;
		this.comment = comment;
	}

	public Comment() {
		super();
		this.likes = 0;
		this.dislike = 0;
		this.status = Status.INACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
