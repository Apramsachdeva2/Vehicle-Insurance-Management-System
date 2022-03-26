package com.cts.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.vehicle.model.Review;
import com.cts.vehicle.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public List<Review> findallreviews() {

		List<Review> lst= reviewRepository.findAll();
		return lst;
	}
	public void saveReview(Review review) {
//		int id=(int) reviewRepository.count();
//        review.setQuestionId(++id);
		reviewRepository.save(review);
	}

	public void deleteReview(int id) {

		reviewRepository.deleteById(id);
		
	}



}
