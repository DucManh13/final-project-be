package com.example.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.DTO.CVDto;
import com.example.finalproject.DTO.RequestCVDto;
import com.example.finalproject.entity.CVEntity;
import com.example.finalproject.repository.ApplicantRepository;
import com.example.finalproject.repository.CVRepository;

@Service
public class CVService {
	@Autowired
	private CVRepository cvRepository;

	@Autowired
	private ApplicantRepository applicantRepository;

	public void createCV(RequestCVDto requestCvDto) {
		CVEntity cv = new CVEntity();
		cv.setCvName(requestCvDto.getCvName());
		cv.setName(requestCvDto.getName());
		cv.setAddress(requestCvDto.getAddress());
		cv.setBirthday(requestCvDto.getBirthday());
		cv.setEmail(requestCvDto.getEmail());
		cv.setGender(requestCvDto.isGender());
		cv.setPhone(requestCvDto.getPhone());
		cv.setPhoto(requestCvDto.getPhoto());
		cv.setObjective(requestCvDto.getObjective());
		cv.setExperience(requestCvDto.getExperience());
		cv.setSkills(requestCvDto.getSkills());
		cv.setApplicant(applicantRepository.findByAccountId(requestCvDto.getAccountId()));
		cvRepository.save(cv);
	}

	public void updateCV(RequestCVDto requestCvDto, int id) {
		CVEntity cv = cvRepository.findById(id).get();
		cv.setCvName(requestCvDto.getCvName());
		cv.setName(requestCvDto.getName());
		cv.setAddress(requestCvDto.getAddress());
		cv.setBirthday(requestCvDto.getBirthday());
		cv.setEmail(requestCvDto.getEmail());
		cv.setGender(requestCvDto.isGender());
		cv.setPhone(requestCvDto.getPhone());
		cv.setPhoto(requestCvDto.getPhoto());
		cv.setObjective(requestCvDto.getObjective());
		cv.setExperience(requestCvDto.getExperience());
		cv.setSkills(requestCvDto.getSkills());
		cvRepository.save(cv);
	}

	public List<CVDto> getCVs(int accountId) {
		List<CVDto> cvs = new ArrayList<>();
		List<CVEntity> cvList = cvRepository
				.findAllByApplicantIdOrderByIdAsc(applicantRepository.findByAccountId(accountId).getId());
		cvList.forEach(cv -> {
			cvs.add(mapCVEntityToDto(cv));
		});
		return cvs;
	}

	public CVDto getCVById(int cvId) {
		CVEntity cv = cvRepository.findById(cvId).get();
		return mapCVEntityToDto(cv);
	}

	public List<CVDto> getCVsByJobId(int jobId) {
		List<CVDto> cvs = new ArrayList<>();
		List<CVEntity> cvList = cvRepository.findAllByJobsId(jobId);
		cvList.forEach(cv -> {
			cvs.add(mapCVEntityToDto(cv));
		});
		return cvs;
	}

	private CVDto mapCVEntityToDto(CVEntity cv) {
		CVDto cvDto = new CVDto();
		cvDto.setId(cv.getId());
		cvDto.setCvName(cv.getCvName());
		cvDto.setName(cv.getName());
		cvDto.setAddress(cv.getAddress());
		cvDto.setBirthday(cv.getBirthday());
		cvDto.setEmail(cv.getEmail());
		cvDto.setGender(cv.isGender());
		cvDto.setPhone(cv.getPhone());
		cvDto.setPhoto(cv.getPhoto());
		cvDto.setObjective(cv.getObjective());
		cvDto.setExperience(cv.getExperience());
		cvDto.setSkills(cv.getSkills());
		return cvDto;
	}
}
