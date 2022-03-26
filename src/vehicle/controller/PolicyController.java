package com.cts.vehicle.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.vehicle.model.Policy;
import com.cts.vehicle.service.PolicyService;
import com.cts.vehicle.service.VehicleInsuranceException;

@Controller
public class PolicyController {

	@Autowired
	PolicyService service;
	
	@RequestMapping(value="/policydetails")
	public String policyPage(@ModelAttribute("policy") Policy policy,HttpSession session) throws VehicleInsuranceException {
		List<Policy> policyList = service.getAllPolicies();
		session.setAttribute("policiesPa", policyList);
		return "policyadminhome";
		
	}
	@PostMapping(value="/savepolicy")
	public String policyPage1(@ModelAttribute("policy") Policy policy) {
		System.out.println(policy);
		service.savePolicy(policy);
		return "redirect:/policydetails";
		
	}
	@RequestMapping(value="/editpolicy/{id}")
	public String policyPage2(@PathVariable("id") int id,@ModelAttribute("policy") Policy policy,ModelMap map) {
		policy=service.getPolicy(id);
		map.addAttribute("policy",policy);
		return "editpolicy";
		
	}
	@RequestMapping(value="/editsavepolicy")
	public String policyPage3(@ModelAttribute("policy") Policy policy,ModelMap map) {
		service.updatePolicy(policy);;
		return "redirect:/policydetails";
	}
	
	@ModelAttribute("policies")
	public List<Policy> getAllPolivy() throws VehicleInsuranceException{
		return service.getAllPolicies();
	}
	@RequestMapping(value="/policyfeatures")
	public String policyfeatures() {
		
		return "policyfeatures";
		
	}
	
	
	
}
