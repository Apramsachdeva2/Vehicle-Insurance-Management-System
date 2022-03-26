package com.cts.vehicle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cts.vehicle.model.Help;
import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.Policy;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.Review;
import com.cts.vehicle.model.User;
import com.cts.vehicle.repository.InsuranceRepository;
import com.cts.vehicle.repository.PolicyAssignRepository;
import com.cts.vehicle.repository.UserRepository;
import com.cts.vehicle.service.HelpService;
import com.cts.vehicle.service.InsuranceService;
import com.cts.vehicle.service.PolicyAssignService;
import com.cts.vehicle.service.PolicyService;
import com.cts.vehicle.service.ReviewService;
import com.cts.vehicle.service.UserService;
import com.cts.vehicle.service.VehicleInsuranceException;

@Controller

//@SessionAttributes({ "myAssignments", "policyAdminIdd", "policies", "userId", "u" })
public class UserController {

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
		System.out.println("Admin Page insurance List:"+userId);
		List<Insurance> insuranceList = service1.insuranceList();
		System.out.println(insuranceList);
		return insuranceList;
	}

	@ModelAttribute("helprequestlist")
	public List<Help> helprequestList() {
		List<Help> helprequestlist = helpService.helprequestlist();
		return helprequestlist;

	}
	int userId;
	
	

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signIn(@ModelAttribute("userLogin") User user, @ModelAttribute("command") Insurance insurance,
			@ModelAttribute("policy") Policy policy, ModelMap map,HttpSession session) throws VehicleInsuranceException {
		String pageName = "";
		if (user.getUserName().equalsIgnoreCase("admin") && user.getPassword().equalsIgnoreCase("admin")) {
			//System.out.println("Admin User List :"+map.getAttribute("userList"));
			session.setAttribute("loggedIn", "admin");
			return "adminNew";
		} else {
			User u = uservice.getApprovedOfficer(user);
			// User rejected=uservice.getRejectedOfficer(user);

			if (u == null) {
				if (uservice.getUserByUsername(user.getUserName()) != null) {
					User user1=uservice.getUserByUsername(user.getUserName());
					if(user1.getStatus()==null ) {
						map.put("passwordmsg", "Registration pending approval");
					}
					else if(user1.getStatus().equalsIgnoreCase("Rejected")) {
						map.put("passwordmsg", "Registration Rejected");
					}
					else {
						map.put("passwordmsg", "Password not matching");
					}
				} else if (uservice.getUserByPassword(user.getPassword()) != null) {
					map.put("unamemsg", "Username not present");
				} else {
					map.put("passwordmsg", "Password not matching");
					map.put("unamemsg", "Username not present");
				}

				return "login";
			}

			else {
				System.out.println(u.getUserCategory());
				//session.setAttribute("userId", u.getUserId());
				if (u.getUserCategory().equalsIgnoreCase("field officer")) {
					userId = u.getUserId();
					session.setAttribute("FoUserId", u.getUserId());
					List<PolicyAssign> lst = pas.getAllPoicyForFo(userId);
					session.setAttribute("myAssignmentsFo", lst);
					session.setAttribute("loggedIn","fieldOfficer");
					System.out.println("loggedIn ="+session.getAttribute("loggedIn"));
					System.out.println("field officer "+session.getAttribute("FoUserId"));
					return "fieldofficerhome";
				}

				if (u.getUserCategory().equalsIgnoreCase("policy admin")) {
					userId = u.getUserId();
					session.setAttribute("PaUserId", u.getUserId());
					List<PolicyAssign> lst = pas.getAllPoicyForPa(userId);
					System.out.println("My Assignment for policy admin: "+lst);
					session.setAttribute("myAssignmentsPa", lst);
					session.setAttribute("policyAdminIdd", userId);
					List<Policy> policyList = pservice.getAllPolicies();
					session.setAttribute("policiesPa", policyList);
					session.setAttribute("loggedIn","policyAdmin");
					System.out.println("Policy Admin "+session.getAttribute("PaUserId"));
					return "policyadminhome";
				}
				if (u.getUserCategory().equalsIgnoreCase("user")) {
					
					List<Policy> policyList = pservice.getAllPolicies();
					List<Insurance> lst = service1.getAllInsuranceByUser(u.getUserId());
					System.out.println("User Insurance List : "+lst);
					session.setAttribute("insuranceListt", lst);
					session.setAttribute("policies", policyList);
					session.setAttribute("custUserId", u.getUserId());
					session.setAttribute("loggedIn","user");
					return "customer";
				}
				session.setAttribute("u", u);
				
				userId = u.getUserId();
			}

			session.setAttribute("u", u);

		}
		return "login";
	}

	

	

	@ModelAttribute("policyfeatures")
	public List<Policy> policyfeatures() throws VehicleInsuranceException {
		List<Policy> policy = pservice.getAllPolicies();

		return policy;
	}

	@ModelAttribute("assignedPolicyList")
	public List<PolicyAssign> assignedPolicyList() throws VehicleInsuranceException {
		List<PolicyAssign> assignedPolicyList = uservice.assignedPolicyList(userId);
		return assignedPolicyList;
	}

	
	

	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public String displayHome(@ModelAttribute("vehicleInsurance") User loginDetails,HttpSession session) {
		System.out.println("CustomerId:"+session.getAttribute("custUserId"));
		System.out.println("FoId:"+session.getAttribute("FoUserId"));
		System.out.println("PaId:"+session.getAttribute("PatUserId"));
		session.invalidate();
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogin(@ModelAttribute("userLogin") User user) {
		return "login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String displayRegister(@ModelAttribute("vehicleInsurance") User user) {
		return "registerNew";
	}

	@RequestMapping(value = "/successfulregistration", method = RequestMethod.POST)
	public String displayRegisterSuccess(@ModelAttribute("vehicleInsurance") User user) {
		if(user.getUserCategory().equalsIgnoreCase("user")) {
			user.setStatus("Approved");
		}
		uservice.registerSuccess(user);
		return "usercreated";
	}

	@RequestMapping(value = "/validateData/{id}")
	public String show(@ModelAttribute("command") Insurance insurance, @PathVariable("id") int id, ModelMap map) {
		insurance = uservice.getInsuranceDetails(id);
		map.put("make", insurance.getMake());
		map.put("model", insurance.getModel());
		map.put("variant", insurance.getVariant());
		map.put("year", insurance.getManufacturingYear());
		map.put("fueltype", insurance.getFuelType());
		return "validateData";
	}

	

	

	

	@RequestMapping(value = "/newpolicy")
	public String newpolicy() {
		return "newpolicy";
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

	@RequestMapping(value = "/forgotId", method = RequestMethod.GET)
	public String forgotId(@ModelAttribute("command") User user) {
		return "forgotId";
	}

	@RequestMapping(value = "/userId", method = RequestMethod.GET)
	public String UserId(@ModelAttribute("command") User user, ModelMap map) throws VehicleInsuranceException {
		System.out.println(user);
		String userName = uservice.forgotId(user);
		map.put("userId", userName);
		return "userId";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotpassword(@ModelAttribute("command") User user) {
		return "forgotpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public String resetpassword(@ModelAttribute("command") User user) throws VehicleInsuranceException {
		boolean reset = uservice.resetPassword(user);
		if (reset) {
			return "resetpassword";
		}
		return "forgotpassword";
	}

	@RequestMapping(value = "/resetedSucess", method = RequestMethod.POST)
	public String resetedPassword(@ModelAttribute("command") User user) throws VehicleInsuranceException {
		uservice.password(user.getPassword());
		return "resetSucess";
	}

	/*
	 * @RequestMapping(value = "/fohome") public String fohome() { return
	 * "fieldofficerhome"; }
	 */
	
}
