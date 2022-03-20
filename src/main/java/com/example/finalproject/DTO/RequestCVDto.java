package com.example.finalproject.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class RequestCVDto {
	private String cvName;
	private String name;
	private boolean gender;
	private String email;
	private String address;
	private String phone;
	private Date birthday;
	private String photo;
	private String objective;
	private String experience;
	private String skills;
	private int accountId;
}
