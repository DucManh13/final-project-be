package com.example.finalproject.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "job")
public class JobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(length = 4000)
	private String description;
	@Column(length = 4000)
	private String requirements;
	@Column(length = 4000)
	private String benefits;
	private String address;
	private String salary;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private CompanyEntity company;

	@ManyToMany
	@JoinTable(name = "job_cv", joinColumns = @JoinColumn(name = "job_id"), inverseJoinColumns = @JoinColumn(name = "cv_id"))
	private Set<CVEntity> cvs;
}