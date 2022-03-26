package com.cts.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.vehicle.model.Help;
import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.Policy;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.User;
import com.cts.vehicle.service.HelpService;
import com.cts.vehicle.service.InsuranceService;
import com.cts.vehicle.service.PolicyAssignService;
import com.cts.vehicle.service.PolicyService;
import com.cts.vehicle.service.UserService;
import com.cts.vehicle.service.VehicleInsuranceException;

@Controller

//@SessionAttributes({ "myAssignments", "policyAdminIdd", "policies", "userId", "u","loggedIn" })
public class AdminController {
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
	
	
	
	@ModelAttribute("policyfeatures")
	public List<Policy> policyfeatures() throws VehicleInsuranceException {
		List<Policy> policy = pservice.getAllPolicies();

		return policy;
	}
	
	
	@ModelAttribute("userCategoryList")
	public List<String> userCategoryList() {
		List<String> userCategory = new ArrayList<String>();
		userCategory.add("Select User Category");
		userCategory.add("User");
		userCategory.add("Policy Admin");
		userCategory.add("Field Officer");
		return userCategory;
	}

	@ModelAttribute("designationList")
	public List<String> designationList() {
		List<String> designation = new ArrayList<String>();
		designation.add("Select Designation");
		designation.add("Junior Officer");
		designation.add("Officer");
		designation.add("Senior Officer");
		designation.add("Manager");
		designation.add("Divisional Manager");
		return designation;
	}

	@ModelAttribute("roleList")
	public List<String> roleList() {
		List<String> role = new ArrayList<String>();
		role.add("Select Role");
		role.add("Processing Officer");
		role.add("Internal Auditor");
		role.add("Marketing Representative");
		role.add("BG Verification Officer");
		return role;
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

	@ModelAttribute("userList")
	public List<User> userList() throws VehicleInsuranceException {
		List<User> userList = uservice.userList();
		return userList;
	}

	@ModelAttribute("fieldOfficerList")
	public List<User> fieldOfficerList() throws VehicleInsuranceException {
		List<User> fieldOfficerList = uservice.fieldOfficerList();
		return fieldOfficerList;
	}

	@ModelAttribute("policyAdminList")
	public List<User> policyAdminList() throws VehicleInsuranceException {
		List<User> policyAdminList = uservice.policyAdminList();
		return policyAdminList;
	}

	@ModelAttribute("insuranceList1")
	public List<Insurance> getAllNewInsurance() throws VehicleInsuranceException {
		List<Insurance> insuranceList = uservice.getAllNewInsurance();
		return insuranceList;
	}

	@ModelAttribute("insuranceList")
	public List<Insurance> getAllInsurance() throws VehicleInsuranceException {
		
		List<Insurance> insuranceList = service1.insuranceList();
		//System.out.println(insuranceList);
		return insuranceList;
	}
	
	
	
	@ModelAttribute("validatedpolicies")
	public List<Insurance> validatedpolicies() throws VehicleInsuranceException {
		List<Insurance> validated = uservice.validatedpolicies();
		return validated;
	}

	

	
	@ModelAttribute("approvedList")
	public List<Insurance> approvedPolicyListDetails() throws VehicleInsuranceException {
		List<Insurance> approvedList = uservice.approvedList("approved");
		return approvedList;

	}

	@ModelAttribute("rejectedList")
	public List<Insurance> rejectedPolicyListDetails() throws VehicleInsuranceException {
		List<Insurance> rejectedList = uservice.rejectedList("rejected");
		return rejectedList;

	}
	
	@ModelAttribute("policyList")
	public List<Insurance> insuranceList() throws VehicleInsuranceException{
		List<Insurance> policyList=service1.insuranceList();
		return policyList;
		
	}
	@ModelAttribute("helprequestlist")
	public List<Help> helprequestList() {
		List<Help> helprequestlist = helpService.helprequestlist();
		return helprequestlist;

	}
	
	@RequestMapping(value = "/admin/help")
	public String help(ModelMap map) throws VehicleInsuranceException {
		
		List<Help> helprequestlist = helpService.helprequestlist();
		map.addAttribute("helprequestlist", helprequestlist);
		
		return "helpAdmin";
	}
	
	@RequestMapping(value = "/admin/policy-admin")
	public String policyadmin(ModelMap map) throws VehicleInsuranceException {
		
		
		List<User> policyAdminList = uservice.policyAdminList();
		map.addAttribute("policyAdminList", policyAdminList);
		
		return "policyAdmin";
	}
	
	
	
	@RequestMapping(value = "/admin")
	public String AdminPage(ModelMap map,HttpSession session) throws VehicleInsuranceException {
		
		System.out.println("loggedIn = "+session.getAttribute("loggedIn"));
		if(session.getAttribute("loggedIn")!="admin") {
			return "redirect:/login";
		}
		
		List<Insurance> policyList=service1.insuranceList();
		map.addAttribute("policyList", policyList);
		
		List<Insurance> rejectedList = uservice.rejectedList("rejected");
		map.addAttribute("rejectedList", rejectedList);
		
		List<Insurance> approvedList = uservice.approvedList("approved");
		map.addAttribute("approvedList", approvedList);
		
		
		List<Insurance> validated = uservice.validatedpolicies();
		map.addAttribute("validatedpolicies", validated);
		
		List<Insurance> insuranceList = service1.insuranceList();
		map.addAttribute("insuranceList", insuranceList);
		
		List<Insurance> insuranceList1 = uservice.getAllNewInsurance();
		map.addAttribute("insuranceList1", insuranceList1);
		
		List<User> policyAdminList = uservice.policyAdminList();
		map.addAttribute("policyAdminList", policyAdminList);
		
		List<User> fieldOfficerList = uservice.fieldOfficerList();
		map.addAttribute("fieldOfficerList", fieldOfficerList);
		System.out.println(fieldOfficerList);
		
		List<User> userList = uservice.userList();
		map.addAttribute("userList", userList);
		
		return "adminNew";
	}
	
	
	@RequestMapping(value = "/approveUser/{id}", method = RequestMethod.GET)
	public String approveUser(@PathVariable("id") Integer id) {
		uservice.approveUser(id);
		return "redirect:/admin";
	}
	
	@RequestMapping(value = "/rejectUser/{id}", method = RequestMethod.GET)
	public String rejectUser(@PathVariable("id") Integer id) {
		uservice.rejectUser(id);
		return "redirect:/admin";

	}
	
	@RequestMapping(value = "/fieldofficer/{id}")
	public String fieldofficer(ModelMap map, @PathVariable("id") int id, User user) {
		user = uservice.getUserDetails(id);
		map.put("userid", user.getUserId());
		map.put("name", user.getFirstName());
		map.put("dob", user.getDateOfBirth());
		map.put("gender", user.getGender());
		map.put("contact", user.getContactNumber());
		map.put("email", user.getEmail());
		map.put("designation", user.getDesignation());
		map.put("role", user.getRole());
		return "fieldofficer";
	}
	@RequestMapping(value = "/policyAdminValues/{id}")
	public String policyAdmin(ModelMap map, @PathVariable("id") int id, User user) {
		user = uservice.getUserDetails(id);
		map.put("userid", user.getUserId());
		map.put("name", user.getFirstName());
		map.put("dob", user.getDateOfBirth());
		map.put("gender", user.getGender());
		map.put("contact", user.getContactNumber());
		map.put("email", user.getEmail());
		map.put("designation", user.getDesignation());
		map.put("role", user.getRole());
		return "policyAdminDetails";
	}
	
	@RequestMapping(value = "/paassign/{id}")
	public String paassign(@PathVariable("id") int policyAdminId, ModelMap map) {
		map.put("policyAdminId", policyAdminId);
		List<Insurance> lst1 = service1.getAllInsuranceForPa("Submitted to policy admin");
		map.addAttribute("policytoadmin", lst1);

		return "paassign";
	}
	
	@RequestMapping(value = "/paassign/selectedpolicy/{paid}/{pid}")
	public String savedet(@PathVariable("paid") int policyAdminId, @PathVariable("pid") int policyId, ModelMap map) {
		map.put("policyAdminId", policyAdminId);
		service1.updateInsuranceStatus(policyId,"Assigned");
		pas.assignPA(policyId, policyAdminId);
//		uservice.savedet(policyAdminId, policyId);
//		Insurance insurance = service1.getInsurance(policyId);
//		insurance.setStatus("Assigned");
//		service1.savedet(insurance);
		return "redirect:/paassign/" + policyAdminId;
	}

	
	
	@RequestMapping(value = "/foassign/{id}")
	public String foassign(@PathVariable("id") int fieldOfficerId,ModelMap map) {
		map.addAttribute("foid",fieldOfficerId);
		return "foassign";
	}
	
	
	@RequestMapping(value = "/foassign/selectedpolicy/{foid}/{poid}")
	public String savedet2(@PathVariable("foid") int fieldOfficerId,@PathVariable("poid") int policyId,ModelMap map) throws VehicleInsuranceException {
		map.put("fieldOfficerId", fieldOfficerId);
		service1.updateInsuranceStatus(policyId,"Submitted to Field Officer");
		pas.assignFO(policyId, fieldOfficerId);
		//service1.savedet(fieldOfficerId, policyId);  
		List<Insurance> policyList=service1.insuranceList();
		map.addAttribute("policyList", policyList);
		return "foassign";
	}
	
	@RequestMapping(value = "/adminhome")
	public String adminhomepage() {
		return "redirect:/admin";
	}
	
	

	/*
	 * @ModelAttribute("assignedPolicyList") public List<PolicyAssign>
	 * assignedPolicyList() throws VehicleInsuranceException { List<PolicyAssign>
	 * assignedPolicyList = uservice.assignedPolicyList(userId); return
	 * assignedPolicyList; }
	 */
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/resolvehelp/{id}", method = RequestMethod.GET)
	public String resolvehelp(@PathVariable("id") int id, @ModelAttribute("help") Help help, ModelMap map) {

		map.addAttribute("id", id);

		return "resolvehelp";

	}

	@RequestMapping(value = "/resolverequest/{id}")
	public String resolverequest(@PathVariable("id") int id, @ModelAttribute("help") Help help) {
		Help requesthelp = helpService.requesthelp(id);
		requesthelp.setResolveRequest(help.getResolveRequest());
		helpService.saveresolvehelp(requesthelp);
		return "resolvehelp";

	}
	
	@RequestMapping("/claimhistory")
	public String claimHistory(ModelMap model) throws VehicleInsuranceException {
		List<Insurance> list= service1.AllInsurance();
		model.addAttribute("list", list);
		return "claimhistory";
	}

}
