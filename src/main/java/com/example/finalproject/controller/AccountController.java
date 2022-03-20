package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.DTO.AccountDto;
import com.example.finalproject.DTO.AccountItemDto;
import com.example.finalproject.DTO.ApplicantDto;
import com.example.finalproject.DTO.CompanyDto;
import com.example.finalproject.service.AccountService;
import com.example.finalproject.service.ApplicantService;
import com.example.finalproject.service.CompanyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ApplicantService applicantService;

	@PostMapping
	public ResponseEntity<?> getUserInfo(@RequestBody AccountDto accountDto) {
		if (accountDto.getRoleId() == 1)
			return new ResponseEntity<>(applicantService.getApplicantByAccountId(accountDto.getId()), HttpStatus.OK);
		else
			return new ResponseEntity<>(companyService.getCompanyByAccountId(accountDto.getId()), HttpStatus.OK);
	}

	@PostMapping("/username")
	public String getUsername(@RequestBody AccountDto accountDto) {
		return accountService.getUsername(accountDto.getId());
	}

	@PutMapping("/applicant")
	public ResponseEntity<String> updateApplicant(@RequestBody ApplicantDto applicantDto) {
		applicantService.updateApplicant(applicantDto);
		return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
	}

	@PutMapping("/company")
	public ResponseEntity<String> updateCompany(@RequestBody CompanyDto companyDto) {
		companyService.updateCompany(companyDto);
		return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
	}

	@GetMapping
	public List<AccountItemDto> getAccounts() {
		return accountService.getAllAccounts();
	}
}
