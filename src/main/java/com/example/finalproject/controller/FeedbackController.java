package com.example.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalproject.DTO.FeedbackDto;
import com.example.finalproject.DTO.RequestFeedbackDto;
import com.example.finalproject.service.FeedbackService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@PostMapping
	public ResponseEntity<String> updateCompany(@RequestBody RequestFeedbackDto requestFeedbackDto) {
		feedbackService.createFeedback(requestFeedbackDto);
		return new ResponseEntity<>("Send feedback successfully!", HttpStatus.OK);
	}

	@GetMapping
	public List<FeedbackDto> getFeedbacks() {
		return feedbackService.getAllFeedbacks();
	}
}
