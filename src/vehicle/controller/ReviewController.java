package com.cts.vehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.vehicle.model.Review;
import com.cts.vehicle.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;

	@RequestMapping(value = "/reviewlist", method = RequestMethod.GET)
	public String showCategories(ModelMap model) {

		model.put("reviewlist", reviewService.findallreviews());
		return "reviewListNew";
	}
	@RequestMapping(value = "/savereview", method = RequestMethod.POST)
	public String success(@ModelAttribute("command") Review review) {

		reviewService.saveReview(review);
		return "redirect:/reviewlist";
	}

	@RequestMapping(value = "/addReview")
	public String addReview(@ModelAttribute("command") Review review) {
		return "addReview";
	}

	@RequestMapping("/deleteReview/{id}")
	public String deleteReview(@PathVariable(value = "id") int id) {

		reviewService.deleteReview(id);
		return "redirect:/reviewlist";
	}





}
