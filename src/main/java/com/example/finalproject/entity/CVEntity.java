package com.example.finalproject.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "cv")
public class CVEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String cvName;
	private String name;
	private boolean gender;
	private String email;
	private String address;
	private String phone;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	private String photo;
	@Column(length = 4000)
	private String objective;
	@Column(length = 4000)
	private String experience;
	@Column(length = 4000)
	private String skills;

	@ManyToOne
	@JoinColumn(name = "applicant_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private ApplicantEntity applicant;

	@ManyToMany(mappedBy = "cvs")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<JobEntity> jobs;
}
