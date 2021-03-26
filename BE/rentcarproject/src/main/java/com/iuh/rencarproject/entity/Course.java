package com.iuh.rencarproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(name = "short_title", nullable = false)
	private String shortTitle;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created", nullable = false)
	private Date dateCreated;

	@Column(nullable = false)
	private Long view;

	@Column(nullable = false)
	private String content;

	@Column(name = "lecture_name")
	private String lectureName;

	@Column(name = "price")
	private Long price;

	private String link;
	
	private int status;

	@ManyToOne
	@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "Category_id", referencedColumnName = "id", nullable = false)
	private Category category;

}
