package com.example.finalproject.DTO;

import lombok.Data;

@Data
public class CompanyDto {
	private int id;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String website;
	private String logo;
	private String description;
}