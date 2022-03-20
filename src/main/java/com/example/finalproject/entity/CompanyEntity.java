package com.example.finalproject.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "company")
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String website;
	private String logo;
	@Column(length = 4000)
	private String description;

	@OneToOne
	@JoinColumn(name = "account_id")
	private AccountEntity account;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private Set<JobEntity> jobs;
}
