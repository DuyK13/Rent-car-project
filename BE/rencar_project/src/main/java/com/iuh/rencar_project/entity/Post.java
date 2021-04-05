package com.iuh.rencar_project.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;

	@Lob
	private String content;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Post_Tag", joinColumns = { @JoinColumn(name = "Post_id") }, inverseJoinColumns = {
			@JoinColumn(name = "Tag_id") })
	private List<Tag> tags = new ArrayList<Tag>();

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;
}
