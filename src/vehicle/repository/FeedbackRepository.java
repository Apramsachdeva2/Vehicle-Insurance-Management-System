package com.cts.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.vehicle.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
