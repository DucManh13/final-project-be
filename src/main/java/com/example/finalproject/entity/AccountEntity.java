package com.example.finalproject.entity;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;
}