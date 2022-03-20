package com.example.finalproject.DTO;

import lombok.Data;

@Data
public class SignUpDto {
	private String username;
	private String password;
	private String name;
	private int age;
	private String email;
	private String address;
	private String website;
	private String phone;
	private int role;
}