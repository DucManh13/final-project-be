package com.example.finalproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.DTO.ApplicantDto;
import com.example.finalproject.entity.ApplicantEntity;
import com.example.finalproject.repository.ApplicantRepository;

@Service
public class ApplicantService {
	@Autowired
	private ApplicantRepository applicantRepository;

	public ApplicantDto getApplicantByAccountId(int accountId) {
		ApplicantEntity applicant = applicantRepository.findByAccountId(accountId);
		return mapApplicantEntityToDto(applicant);
	}

	public void updateApplicant(ApplicantDto applicantDto) {
		ApplicantEntity applicant = applicantRepository.findById(applicantDto.getId()).get();
		applicant.setName(applicantDto.getName());
		applicant.setAddress(applicantDto.getAddress());
		applicant.setEmail(applicantDto.getEmail());
		applicant.setAge(applicantDto.getAge());
		applicantRepository.save(applicant);
	}

	private ApplicantDto mapApplicantEntityToDto(ApplicantEntity applicant) {
		ApplicantDto applicantDto = new ApplicantDto();
		applicantDto.setId(applicant.getId());
		applicantDto.setName(applicant.getName());
		applicantDto.setEmail(applicant.getEmail());
		applicantDto.setAge(applicant.getAge());
		applicantDto.setAddress(applicant.getAddress());
		return applicantDto;
	}
}
