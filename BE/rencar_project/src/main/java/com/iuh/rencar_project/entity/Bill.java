package com.iuh.rencar_project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.iuh.rencar_project.utils.enums.PaymentType;

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
@Table(name = "Bill")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	String fullname;

	@Column(unique = true)
	String slug;

	@Column(name = "phone_number", nullable = false)
	String phoneNumber;

	@Column(nullable = false)
	String email;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "date_set")
	Date dateSet;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "created_date", nullable = false)
	Date createdDate;

	@Column(nullable = false)
	Long time;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type", nullable = false)
	PaymentType paymentType;

	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "bills_courses", joinColumns = { @JoinColumn(name = "bill_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	List<Course> courses = new ArrayList<Course>();

	@ManyToOne
	@JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
	Car car;
}
