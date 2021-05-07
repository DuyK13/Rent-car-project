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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.iuh.rencar_project.utils.enums.CarType;

@Entity
@Table(name = "cars", uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	String name;

	@Column(nullable = false)
	int year;

	@Column(nullable = false)
	Long price;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	CarType type;

	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "created_by", referencedColumnName = "id")
	private User createdBy;

	@CreatedDate
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@LastModifiedBy
	@ManyToOne
	@JoinColumn(name = "modified_by", referencedColumnName = "id")
	private User modifiedBy;

	@LastModifiedDate
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "modefied_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	public Car(Long id, String name, int year, Long price, CarType type, User createdBy, Date createdDate,
			User modifiedBy, Date modifiedDate) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.price = price;
		this.type = type;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
	}

	public Car() {
		super();
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public CarType getType() {
		return type;
	}

	public void setType(CarType type) {
		this.type = type;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
