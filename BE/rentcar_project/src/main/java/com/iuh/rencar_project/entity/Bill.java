package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.PaymentType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bills", uniqueConstraints = { @UniqueConstraint(columnNames = "slug") })
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false)
	private String slug;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String email;

	@CreatedDate
	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm tt")
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm tt")
	@Column(name = "time_start")
	private LocalDateTime timeStart;

//	@Enumerated(EnumType.STRING)
//	@Column(name = "payment_type")
//	PaymentType paymentType;

	@Column(name = "extra_time")
	private Long extraTime;

	@ManyToMany
	@JoinTable(name = "bills_courses", joinColumns = { @JoinColumn(name = "bill_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<Course> courses = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;

	public Bill(Long id, String fullname, String slug, String phoneNumber, String email, Date createdDate, LocalDateTime timeStart, Long extraTime, Set<Course> courses, Car car) {
		this.id = id;
		this.fullname = fullname;
		this.slug = slug;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.createdDate = createdDate;
		this.timeStart = timeStart;
		this.extraTime = extraTime;
		this.courses = courses;
		this.car = car;
	}

	public Bill() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Long getExtraTime() {
		return extraTime;
	}

	public void setExtraTime(Long extraTime) {
		this.extraTime = extraTime;
	}
}
