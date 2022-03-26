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
import com.cts.vehicle.model.Policy;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.User;
import com.cts.vehicle.service.HelpService;
import com.cts.vehicle.service.InsuranceService;
import com.cts.vehicle.service.PolicyAssignService;
import com.cts.vehicle.service.PolicyService;
import com.cts.vehicle.service.UserService;

@Controller

//@SessionAttributes({ "myAssignments", "policyAdminIdd", "policies", "userId", "u" })
public class PAController {

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
	
	
	@RequestMapping(value = "/policystatus/{id}/{status}", method = RequestMethod.GET)
	public String policyStatus(@ModelAttribute("policy") Policy policy, @PathVariable("status") String status,
			@PathVariable("id") int id, @ModelAttribute("insurance") Insurance insurance, ModelMap map,HttpSession session) {
		if(status.equalsIgnoreCase("approved")) {
			service1.updateInsuranceStatus(id,"Approved");
			pas.updateAssignmentStatus(id, "Approved");
			}
			else {
				service1.updateInsuranceStatus(id,"Rejected");
				pas.updateAssignmentStatus(id, "Rejected");
			}

		List<PolicyAssign> lst = pas.getAllPoicyForPa((int) session.getAttribute("PaUserId"));
		System.out.println("My Assignment for policy admin: "+lst);
		session.setAttribute("myAssignmentsPa", lst);
		return "policyadminhome";
	}
	
	@RequestMapping(value = "/policyadminvalidation/{id}", method = RequestMethod.GET)

	public String policyadminvalidation(@PathVariable("id") int id, @ModelAttribute("insurance") Insurance insurance,

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
		map.put("flg", true);
		return "paview";

	}
	@RequestMapping(value = "/pahome")
	public String validate(Policy policy,HttpSession session) {
		
		if(session.getAttribute("loggedIn")!="policyAdmin") {
			return "redirect:/login";
		}
		return "policyadminhome";

	}
	
	@RequestMapping("/viewpolicyadminprofile")
	public String viewPolicyAdminProfile(@ModelAttribute("command") User user, ModelMap map, HttpSession session) {
		User user1 = uservice.getUserDetails((int) session.getAttribute("PaUserId"));
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
		return"viewpolicyadmindetails";
}
	
	@PostMapping(value = "/editsavepadetails")
	public String editsaveofficerdetails(@ModelAttribute("command") User user, ModelMap map) {
		uservice.updatecustomerdetails(user);
		return "redirect:/pahome";
	}
	
}
