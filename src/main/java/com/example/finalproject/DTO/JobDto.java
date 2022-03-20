package com.example.finalproject.DTO;

import lombok.Data;

@Data
public class JobDto {
	private int id;
	private String name;
	private String description;
	private String requirements;
	private String benefits;
	private String address;
	private String salary;
	private CompanyDto company;
}