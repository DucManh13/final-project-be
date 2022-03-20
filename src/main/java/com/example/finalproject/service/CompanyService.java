package com.example.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.DTO.CompanyDto;
import com.example.finalproject.DTO.JobDto;
import com.example.finalproject.entity.CompanyEntity;
import com.example.finalproject.entity.JobEntity;
import com.example.finalproject.repository.CompanyRepository;
import com.example.finalproject.repository.JobRepository;

@Service
public class CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private JobRepository jobRepository;

	public List<CompanyDto> getAllCompanies() {
		List<CompanyDto> companies = new ArrayList<>();
		List<CompanyEntity> companyList = companyRepository.findAllByOrderByIdAsc();
		companyList.forEach(company -> {
			companies.add(mapCompanyEntityToDto(company));
		});
		return companies;
	}

	public CompanyDto getCompanyById(int companyId) {
		CompanyEntity company = companyRepository.findById(companyId).get();
		return mapCompanyEntityToDto(company);
	}

	public CompanyDto getCompanyByAccountId(int accountId) {
		CompanyEntity company = companyRepository.findByAccountId(accountId);
		return mapCompanyEntityToDto(company);
	}

	public List<JobDto> getJobsByCompany(int companyId) {
		List<JobDto> jobs = new ArrayList<>();
		List<JobEntity> jobList = jobRepository.findAllByCompanyIdOrderByIdAsc(companyId);
		jobList.forEach(company -> {
			jobs.add(mapJobEntityToDto(company));
		});
		return jobs;
	}

	public void updateCompany(CompanyDto companyDto) {
		CompanyEntity company = companyRepository.findById(companyDto.getId()).get();
		company.setName(companyDto.getName());
		company.setEmail(companyDto.getEmail());
		company.setAddress(companyDto.getAddress());
		company.setPhone(companyDto.getPhone());
		company.setWebsite(companyDto.getWebsite());
		company.setLogo(companyDto.getLogo());
		company.setDescription(companyDto.getDescription());
		companyRepository.save(company);
	}

	private CompanyDto mapCompanyEntityToDto(CompanyEntity company) {
		CompanyDto companyDto = new CompanyDto();
		companyDto.setId(company.getId());
		companyDto.setName(company.getName());
		companyDto.setEmail(company.getEmail());
		companyDto.setAddress(company.getAddress());
		companyDto.setPhone(company.getPhone());
		companyDto.setWebsite(company.getWebsite());
		companyDto.setLogo(company.getLogo());
		companyDto.setDescription(company.getDescription());
		return companyDto;
	}

	private JobDto mapJobEntityToDto(JobEntity job) {
		JobDto jobDto = new JobDto();
		jobDto.setId(job.getId());
		jobDto.setName(job.getName());
		jobDto.setDescription(job.getDescription());
		jobDto.setRequirements(job.getRequirements());
		jobDto.setBenefits(job.getBenefits());
		jobDto.setAddress(job.getAddress());
		jobDto.setSalary(job.getSalary());
		jobDto.setCompany(mapCompanyEntityToDto(job.getCompany()));
		return jobDto;
	}

}
