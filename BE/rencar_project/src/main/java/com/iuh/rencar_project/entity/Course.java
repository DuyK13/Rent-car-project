package com.iuh.rencar_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "courses", uniqueConstraints = { @UniqueConstraint(columnNames = "title"),
		@UniqueConstraint(columnNames = "slug") })
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Long price;

	@Column(nullable = false)
	private String slug;

	@Column(name = "time_course", nullable = false)
	private Long timeCourse;

	public Course(Long id, String title, Long price, String slug, Long timeCourse) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.slug = slug;
		this.timeCourse = timeCourse;
	}

	public Course() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Long getTimeCourse() {
		return timeCourse;
	}

	public void setTimeCourse(Long timeCourse) {
		this.timeCourse = timeCourse;
	}
}
