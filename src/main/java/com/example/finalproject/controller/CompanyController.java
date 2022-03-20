package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.DTO.CompanyDto;
import com.example.finalproject.DTO.JobDto;
import com.example.finalproject.service.CompanyService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@GetMapping
	public List<CompanyDto> getCompanies() {
		return companyService.getAllCompanies();
	}

	@GetMapping("/{id}")
	public CompanyDto getCompany(@PathVariable int id) {
		return companyService.getCompanyById(id);
	}

	@GetMapping("/{id}/job")
	public List<JobDto> getJobs(@PathVariable int id) {
		return companyService.getJobsByCompany(id);
	}
}