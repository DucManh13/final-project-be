package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.DTO.AccountDto;
import com.example.finalproject.DTO.ApplyDto;
import com.example.finalproject.DTO.CVDto;
import com.example.finalproject.DTO.CheckApplyDto;
import com.example.finalproject.DTO.JobDto;
import com.example.finalproject.DTO.RequestJobDto;
import com.example.finalproject.service.CVService;
import com.example.finalproject.service.JobService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/job")
public class JobController {
	@Autowired
	private JobService jobService;

	@Autowired
	private CVService cvService;

	@GetMapping
	public List<JobDto> getJobs() {
		return jobService.getAllJobs();
	}

	@GetMapping("/{id}")
	public JobDto getJob(@PathVariable int id) {
		return jobService.getJobById(id);
	}

	@GetMapping("/{id}/applicants")
	public List<CVDto> getApplicants(@PathVariable int id) {
		return cvService.getCVsByJobId(id);
	}

	@PostMapping
	public List<JobDto> getJobs(@RequestBody AccountDto accountDto) {
		return jobService.getJobsByCompany(accountDto.getId());
	}

	@PostMapping("/new")
	public ResponseEntity<String> create(@RequestBody RequestJobDto requestJobDto) {
		jobService.createJob(requestJobDto);
		return new ResponseEntity<>("Create Job successfully!.", HttpStatus.OK);
	}

	@PostMapping("/apply")
	public ResponseEntity<String> apply(@RequestBody ApplyDto applyDto) {
		jobService.addCVToJob(applyDto);
		return new ResponseEntity<>("Apply to job successfully!.", HttpStatus.OK);
	}

	@PostMapping("/check-apply")
	public Boolean checkApply(@RequestBody CheckApplyDto checkApplyDto) {
		return jobService.checkApply(checkApplyDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateJob(@PathVariable int id, @RequestBody RequestJobDto requestJobDto) {
		jobService.updateJob(requestJobDto, id);
		return new ResponseEntity<>("Update successfully!", HttpStatus.OK);
	}
}