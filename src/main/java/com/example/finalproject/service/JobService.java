package com.example.finalproject.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.DTO.ApplyDto;
import com.example.finalproject.DTO.CheckApplyDto;
import com.example.finalproject.DTO.CompanyDto;
import com.example.finalproject.DTO.JobDto;
import com.example.finalproject.DTO.RequestJobDto;
import com.example.finalproject.entity.CVEntity;
import com.example.finalproject.entity.CompanyEntity;
import com.example.finalproject.entity.JobEntity;
import com.example.finalproject.repository.ApplicantRepository;
import com.example.finalproject.repository.CVRepository;
import com.example.finalproject.repository.CompanyRepository;
import com.example.finalproject.repository.JobRepository;

@Service
public class JobService {
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CVRepository cvRepository;

	public List<JobDto> getAllJobs() {
		List<JobDto> jobs = new ArrayList<>();
		List<JobEntity> jobList = jobRepository.findAllByOrderByIdAsc();
		jobList.forEach(job -> {
			jobs.add(mapJobEntityToDto(job));
		});
		return jobs;
	}

	public JobDto getJobById(int jobId) {
		JobEntity job = jobRepository.findById(jobId).get();
		return mapJobEntityToDto(job);
	}

	public List<JobDto> getJobsByCompany(int accountId) {
		List<JobDto> jobs = new ArrayList<>();
		List<JobEntity> jobList = jobRepository
				.findAllByCompanyIdOrderByIdAsc(companyRepository.findByAccountId(accountId).getId());
		jobList.forEach(job -> {
			jobs.add(mapJobEntityToDto(job));
		});
		return jobs;
	}

	public void createJob(RequestJobDto requestJobDto) {
		JobEntity job = new JobEntity();
		job.setName(requestJobDto.getName());
		job.setSalary(requestJobDto.getSalary());
		job.setAddress(requestJobDto.getAddress());
		job.setDescription(requestJobDto.getDescription());
		job.setRequirements(requestJobDto.getRequirements());
		job.setBenefits(requestJobDto.getBenefits());
		job.setCompany(companyRepository.findByAccountId(requestJobDto.getAccountId()));
		jobRepository.save(job);
	}

	public void updateJob(RequestJobDto requestJobDto, int id) {
		JobEntity job = jobRepository.findById(id).get();
		job.setName(requestJobDto.getName());
		job.setSalary(requestJobDto.getSalary());
		job.setAddress(requestJobDto.getAddress());
		job.setDescription(requestJobDto.getDescription());
		job.setRequirements(requestJobDto.getRequirements());
		job.setBenefits(requestJobDto.getBenefits());
		jobRepository.save(job);
	}

	public void addCVToJob(ApplyDto applyDto) {
		JobEntity job = jobRepository.findById(applyDto.getJobId()).get();
		Set<CVEntity> cvs = new HashSet<>();
		cvs.addAll(job.getCvs());
		cvs.add(cvRepository.findById(applyDto.getCvId()).get());
		job.setCvs(cvs);
		jobRepository.save(job);
	}

	public Boolean checkApply(CheckApplyDto checkApplyDto) {
		return jobRepository.existsByIdAndCvsApplicantId(checkApplyDto.getJobId(),
				applicantRepository.findByAccountId(checkApplyDto.getAccountId()).getId());
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

}
