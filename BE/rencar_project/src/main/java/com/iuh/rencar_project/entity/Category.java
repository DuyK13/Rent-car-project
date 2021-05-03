package com.iuh.rencar_project.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.iuh.rencar_project.utils.enums.Status;

@Entity
@Table(name = "categories", uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "slug") })
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String slug;

	@Column(nullable = false)
	private String description;

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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@OneToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Category category;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "category_id")
	private Set<Car> cars = new HashSet<Car>();

	public Category(Long id, String name, String slug, String description, User createdBy, Date createdDate,
			User modifiedBy, Date modifiedDate, Status status, Category category, Set<Car> cars) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.category = category;
		this.cars = cars;
	}

	public Category() {
		super();
		this.status = Status.ACTIVE;
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Car> getCars() {
		return cars;
	}

}
