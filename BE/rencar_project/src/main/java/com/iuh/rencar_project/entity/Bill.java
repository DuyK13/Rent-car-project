package com.iuh.rencar_project.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bill")
@ToString
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String fullname;

	@Column(name = "phone_number", nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "date_set")
	private Date dateSet;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(nullable = false)
	private Long time;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_type", nullable = false)
	private PaymentType paymentType;


	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Bill_Course", joinColumns = { @JoinColumn(name = "Bill_id") }, inverseJoinColumns = {
			@JoinColumn(name = "Course_id") })
	private List<Course> courses;
	
	@ManyToOne
	@JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
	private Car car;
}
