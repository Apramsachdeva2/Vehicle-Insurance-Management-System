package com.cts.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.vehicle.model.Feedback;
import com.cts.vehicle.model.Help;
import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.Policy;
import com.cts.vehicle.model.Review;
import com.cts.vehicle.model.User;
import com.cts.vehicle.service.FeedbackService;
import com.cts.vehicle.service.HelpService;
import com.cts.vehicle.service.InsuranceService;
import com.cts.vehicle.service.PolicyAssignService;
import com.cts.vehicle.service.PolicyService;
import com.cts.vehicle.service.ReviewService;
import com.cts.vehicle.service.UserService;
import com.cts.vehicle.service.VehicleInsuranceException;

@Controller

//@SessionAttributes({ "myAssignments", "policyAdminIdd", "policies", "userId", "u" })
public class CustomerController {

	
	@Autowired
	UserService uservice;
	@Autowired
	InsuranceService service1;
	@Autowired
	PolicyService pservice;
	@Autowired
	PolicyAssignService pas;
	@Autowired
	HelpService helpService;
	@Autowired
	ReviewService rservice;
	@Autowired
	FeedbackService fservice;
	
	@ModelAttribute("helprequestlist")
	public List<Help> helprequestList() {
		List<Help> helprequestlist = helpService.helprequestlist();
		return helprequestlist;

	}
	@ModelAttribute("policyfeatures")
	public List<Policy> policyfeatures() throws VehicleInsuranceException {
		List<Policy> policy = pservice.getAllPolicies();

		return policy;
	}
	
	@ModelAttribute("secretQuestion")
	public List<String> secretQuestions() {
		List<String> security = new ArrayList<String>();
		security.add("Select a Security Question");
		security.add("What is your mother's date of birth?");
		security.add("What is your father's date of birth?");
		security.add("What is the name of your first pet?");
		security.add("What was your first car?");
		security.add("What elementary school did you attend?");
		security.add("What is the name of the town where you were born?");
		return security;
	}
	
	
	@RequestMapping(value = "/customerhome")
	public String customerhome(@ModelAttribute("command") Insurance insurance,HttpSession session) throws VehicleInsuranceException {
		
//		List<Insurance> lst = service1.getAllInsuranceById(u.getUserId());
//		System.out.println("User Insurance List : "+lst);
//		map.addAttribute("insuranceListt", lst);
//		List<Policy> policyList = pservice.getAllPolicies();
//		map.put("policies", policyList);
		List<Policy> policyList = pservice.getAllPolicies();
		session.setAttribute("policies", policyList);
		List<Insurance> l1 = service1.getAllInsuranceByUser((int) session.getAttribute("custUserId"));
		session.setAttribute("insuranceListt", l1);
		return "customer";
	}
	

	@RequestMapping(value = "/viewcustomerdetails", method = RequestMethod.GET)
	public String viewcustomerdetails(@ModelAttribute("viewcustomerdetails") User user, ModelMap map,HttpSession session) {
		User customerDetails = uservice.getCustomerDetails((int) session.getAttribute("custUserId"));
		map.put("cdetails", customerDetails);

		return "viewcustomerdetails";
	}
	
	@RequestMapping(value = "/editcustomerdetails/{id}", method = RequestMethod.GET)
	public String editcustomerdetails(@PathVariable("id") int id, @ModelAttribute("customerdetails") User user,
			ModelMap map) {
		User customerDetails = uservice.getCustomerDetails(id);

		map.put("cdetails", customerDetails);
		return "editcustomerdetails";

	}
	@PostMapping(value = "/editsavecustomerdetails")
	public String editsavecustomerdetails(@ModelAttribute("customerdetails") User user, ModelMap map) {
		uservice.updatecustomerdetails(user);
		return "redirect:/viewcustomerdetails";
	}

//	@RequestMapping(value = "/customerr/{id}", method = RequestMethod.GET)
//	public String customerLogin1(@PathVariable("id") int id, @ModelAttribute("userLogin") User user,
//			@ModelAttribute("command") Insurance insurance, ModelMap map,HttpSession session) throws VehicleInsuranceException {
//		List<Insurance> l1 = service1.getAllInsuranceById(id);
//		session.setAttribute("insuranceListt", l1);
//		return "customer";
//	}
	
	@RequestMapping(value = "/feedback")
	public String feedback1(@ModelAttribute("insurancef") Insurance insurance, HttpSession session,
			ModelMap model) {
		System.out.println( session.getAttribute("insuranceId"));
		Insurance insurance1 = service1.getInsurance((int) session.getAttribute("insuranceId"));
		
		insurance1.setFeedBackText("Submitted");
		service1.savedet(insurance1);

		return "redirect:/customerhome";
	}
	
	@RequestMapping(value = "/feedBackForm/{id}")
	public String feedback(@ModelAttribute("insurancef") Insurance insurance, @PathVariable("id") int id,
			ModelMap model,HttpSession session) {
		session.setAttribute("insuranceId", id);
		List<Review> qlst=rservice.findallreviews();
		session.setAttribute("questions",qlst);
		
		return "redirect:/feedbackmid";
	}
	@RequestMapping(value="/feedbackmid")
	public String feedbackMid(@ModelAttribute("insurancef") Insurance insurance,HttpSession session,ModelMap map,@ModelAttribute("feedback") Feedback feedback) {
		
//		System.out.println(feedback.getAnsId());
//		System.out.println(feedback.getQuesId());
//		System.out.println(feedback.getUserId());
//		
//		if(feedback.getAnsId()==0) {
//			feedback.setQuesId((int) session.getAttribute("ques_id"));
//			feedback.setUserId((int) session.getAttribute("userid"));
//			fservice.saveFeedback(feedback);
//		}
		
		List<Review> qlst=(List<Review>) session.getAttribute("questions");
		if(qlst.isEmpty()) {
			System.out.println("No questions available");
			map.put("feedbackFlag",true);
			
		}
		else {
			map.put("feedbackFlag",false);
			map.put("currQues", qlst.get(0));
			session.setAttribute("ques_id", qlst.get(0).getQuestionId());
		qlst.remove(0);
		session.setAttribute("questions", qlst);
		}
		return "feedback";
	}
	
	@PostMapping("/feedbackSave")
	public String feedbackSave(@ModelAttribute("feedback") Feedback feedback,HttpSession session) {
		feedback.setQuesId((int) session.getAttribute("ques_id"));
		feedback.setUserId((int) session.getAttribute("custUserId"));
		fservice.saveFeedback(feedback);
	return "redirect:/feedbackmid";
	}

	
	
	
	@RequestMapping(value = "/viewhelppage")
	public String viewhelppage(@ModelAttribute("help") Help help, ModelMap map ,HttpSession session) {
		List<Help> lst=helpService.findAllById((int) session.getAttribute("custUserId"));
		map.addAttribute("helpList", lst);
		map.addAttribute("userId",session.getAttribute("custUserId") );
		System.out.println("help list:"+lst);
		return "help";
	}

	@RequestMapping(value = "/savehelppage/{userId}")
	public String savehelppage(@ModelAttribute("help") Help help, ModelMap map ,@PathVariable("userId") int userId) {

		help.setUserId(userId);
		helpService.savehelp(help);
		map.addAttribute("msg", "Your help request has been submitted");
		List<Help> lst=helpService.findAllById(userId);
		map.addAttribute("helpList", lst);
		return "help";
	}
	
}
