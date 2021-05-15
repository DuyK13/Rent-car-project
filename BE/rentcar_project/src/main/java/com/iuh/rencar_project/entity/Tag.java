package com.iuh.rencar_project.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tags", uniqueConstraints = { @UniqueConstraint(columnNames = "slug"),
		@UniqueConstraint(columnNames = "name") })
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String slug;

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

	public Tag() {
		super();
	}

	public Tag(Long id, String name, String slug, User createdBy, Date createdDate, User modifiedBy,
			Date modifiedDate) {
		super();
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
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
}
