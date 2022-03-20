package com.example.finalproject.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class AdminEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@OneToOne
	@JoinColumn(name = "account_id")
	private AccountEntity account;
}

