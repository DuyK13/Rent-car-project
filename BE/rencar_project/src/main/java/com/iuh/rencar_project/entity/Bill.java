package com.iuh.rencar_project.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.iuh.rencar_project.utils.enums.PaymentType;

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
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm tt")
	@Column(name = "time_start")
	private LocalDateTime timeStart;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type")
	PaymentType paymentType;

	@ManyToMany
	@JoinTable(name = "bills_courses", joinColumns = { @JoinColumn(name = "bill_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<Course> courses = new HashSet<Course>();

	@ManyToOne
	@JoinColumn(name = "car_id", referencedColumnName = "id")
	private Car car;

	public Bill(Long id, String fullname, String slug, String phoneNumber, String email, Date createdDate,
			LocalDateTime timeStart, PaymentType paymentType, Set<Course> courses, Car car) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.slug = slug;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.createdDate = createdDate;
		this.timeStart = timeStart;
		this.paymentType = paymentType;
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
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

}
