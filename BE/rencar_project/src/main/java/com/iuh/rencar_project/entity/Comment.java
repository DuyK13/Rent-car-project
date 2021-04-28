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

import com.iuh.rencar_project.utils.enums.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	String email;

	@Column(name = "created_date", nullable = false)
	Date createdDate;

	@Column(nullable = false)
	String content;

	@Column(nullable = false)
	int likes;

	@Column(nullable = false)
	int dislike;

	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	Status status;

	@OneToOne
	@JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
	Comment comment;
}
