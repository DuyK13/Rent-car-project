package com.iuh.rencar_project.entity;

import com.iuh.rencar_project.utils.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = { @UniqueConstraint(columnNames = "name") }, indexes = {@Index(name = "IDX_Search", columnList = "name")})
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ERole name;

	public Role(Long id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public Role() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", name=" + name +
				'}';
	}
}
