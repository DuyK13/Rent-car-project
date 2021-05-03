package com.iuh.rencar_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
}
