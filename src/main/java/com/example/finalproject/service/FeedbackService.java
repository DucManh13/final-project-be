package com.example.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finalproject.DTO.FeedbackDto;
import com.example.finalproject.DTO.RequestFeedbackDto;
import com.example.finalproject.entity.FeedbackEntity;
import com.example.finalproject.repository.AccountRepository;
import com.example.finalproject.repository.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private AccountRepository accountRepository;

	public void createFeedback(RequestFeedbackDto requestFeedbackDto) {
		FeedbackEntity feedback = new FeedbackEntity();
		feedback.setContent(requestFeedbackDto.getContent());
		feedback.setAccount(accountRepository.findById(requestFeedbackDto.getAccountId()).get());
		feedbackRepository.save(feedback);
	}

	public List<FeedbackDto> getAllFeedbacks() {
		List<FeedbackDto> feedbacks = new ArrayList<>();
		List<FeedbackEntity> feedbackList = feedbackRepository.findAllByOrderByIdAsc();
		feedbackList.forEach(feedback -> {
			FeedbackDto feedbackDto = new FeedbackDto();
			feedbackDto.setId(feedback.getId());
			feedbackDto.setContent(feedback.getContent());
			feedbackDto.setUsername(feedback.getAccount().getUsername());
			feedbacks.add(feedbackDto);
		});
		return feedbacks;
	}
}
