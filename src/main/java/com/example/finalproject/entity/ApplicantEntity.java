package com.example.finalproject.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "applicant")
public class ApplicantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int age;
	private String email;
	private String address;

	@OneToOne
	@JoinColumn(name = "account_id")
	private AccountEntity account;

	@OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
	private Set<CVEntity> cvs;
}
