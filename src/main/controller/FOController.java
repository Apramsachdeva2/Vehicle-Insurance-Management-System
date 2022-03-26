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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.User;
import com.cts.vehicle.service.HelpService;
import com.cts.vehicle.service.InsuranceService;
import com.cts.vehicle.service.PolicyAssignService;
import com.cts.vehicle.service.PolicyService;
import com.cts.vehicle.service.UserService;

@Controller

//@SessionAttributes({ "myAssignments", "policyAdminIdd", "policies", "userId", "u" })
public class FOController {
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
	
	@RequestMapping(value = "/fieldofficerhome")
	public String validate(HttpSession session) {
		System.out.println(session.getAttribute("loggedIn"));
		if(session.getAttribute("loggedIn")!="fieldOfficer") {
			return "redirect:/login";
		}
		
		return "fieldofficerhome";
	}
	
	@RequestMapping(value = "/fieldofficervalidation/{id}", method = RequestMethod.GET)
	public String fieldofficervalidation(@PathVariable("id") int id, @ModelAttribute("insurance") Insurance insurance,
			ModelMap map) {
		insurance = service1.getInsurance(id);

		map.addAttribute("firstName", insurance.getFirstName());
		map.addAttribute("lastName", insurance.getLastName());
		map.addAttribute("address", insurance.getAddress());
		map.addAttribute("registrationnumber", insurance.getVehicleRegistrationNumber());
		
		map.addAttribute("mobilenumber", insurance.getMobileNumber());
		map.addAttribute("email", insurance.getEmailId());
		map.addAttribute("cityofregistration", insurance.getCityOfRegistration());
		map.addAttribute("registrationdate", insurance.getRegistrationDate());
		map.addAttribute("make", insurance.getMake());
		map.addAttribute("model", insurance.getModel());
		map.addAttribute("variant", insurance.getVariant());
		map.addAttribute("year", insurance.getManufacturingYear());
		map.addAttribute("fueltype", insurance.getFuelType());
		map.addAttribute("policyId", insurance.getPolicyId());
		System.out.println(map);
		map.put("flg", true);
		return "foview";
	}
	@RequestMapping(value = "/viewofficerprofile")
	public String viewProfile(@ModelAttribute("command") User user, ModelMap map,HttpSession session) {
		User user1 = uservice.getUserDetails((int) session.getAttribute("FoUserId"));
		map.put("firstName", user1.getFirstName());
		map.put("lastName", user1.getLastName());
		map.put("dob", user1.getDateOfBirth());
		map.put("gender", user1.getGender());
		map.put("phno", user1.getContactNumber());
		map.put("email", user1.getEmail());
		map.put("designation", user1.getDesignation());
		map.put("role", user1.getRole());
		map.put("userName", user1.getUserName());
		map.put("password", user1.getPassword());
		map.put("userCategory", user1.getUserCategory());
		map.put("secretQuestion", user1.getSecretQuestion());
		map.put("secretAnswer", user1.getSecretAnswer());
		return "viewofficerdetails";
	}
	
	@PostMapping(value = "/editsaveofficerdetails")
	public String editsaveofficerdetails(@ModelAttribute("command") User user, ModelMap map) {
		uservice.updatecustomerdetails(user);
		return "redirect:/fieldofficerhome";
	}
	
	@RequestMapping(value = "/updatepolicystatus/{id}/{status}")
	public String updatePolicyStatus(@PathVariable("id") int policyId, @PathVariable("status") String status , ModelMap map,HttpSession session) {
		System.out.println(policyId);
		//Insurance insurance=service1.getInsurance(policyId);
		if(status.equalsIgnoreCase("approve")) {
		service1.updateInsuranceStatus(policyId,"Submitted to policy admin");
		pas.updateAssignmentStatus(policyId, "Verified");
		}
		else {
			service1.updateInsuranceStatus(policyId,"Rejected");
			pas.updateAssignmentStatus(policyId, "Rejected");
		}
		//service1.savedet(insurance);
		List<PolicyAssign> lst = pas.getAllPoicyForFo((int) session.getAttribute("FoUserId"));
		session.setAttribute("myAssignmentsFo", lst);
		return "redirect:/fieldofficerhome";
	}
	
	
}
