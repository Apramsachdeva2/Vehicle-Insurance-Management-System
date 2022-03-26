package com.cts.vehicle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.vehicle.model.Feedback;
import com.cts.vehicle.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository repo;
	
	public void saveFeedback(Feedback f) {
		int id=(int) repo.count();
	f.setAnsId(++id);
	repo.saveAndFlush(f);
	
	
}
}
