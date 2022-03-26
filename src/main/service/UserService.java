package com.cts.vehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.User;
import com.cts.vehicle.repository.InsuranceRepository;
import com.cts.vehicle.repository.PolicyAssignRepository;
import com.cts.vehicle.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;
	@Autowired
	InsuranceRepository repo1;
	@Autowired
	PolicyAssignRepository repo2;

	public List<User> userList() throws VehicleInsuranceException{
		List<User> userList = repo.findByStatusIsNull();
		return userList;
	}

	public List<User> fieldOfficerList() throws VehicleInsuranceException{
		List<User> fieldOfficerList = repo.findByUserCategoryAndStatusIs("Field Officer", "Approved");
		return fieldOfficerList;
	}

	public List<User> policyAdminList() throws VehicleInsuranceException {
		List<User> policyAdminList = repo.findByUserCategoryAndStatusIs("Policy Admin", "Approved");
		return policyAdminList;
	}

	public List<Insurance> getAllNewInsurance() throws VehicleInsuranceException{
		List<Insurance> insuranceList = repo1.findByStatusIs("Customer submitted");
		return insuranceList;
	}

	public List<Insurance> validatedpolicies() throws VehicleInsuranceException{
		List<Insurance> validated = repo1.findByStatusIs("Submitted to policy admin");
		return validated;
	}

	public User getUserDetails(int id) {
		User user = repo.findById(id).get();
		return user;
	}

	public Insurance getInsuranceDetails(int id) {
		Insurance insurance = repo1.findById(id).get();
		return insurance;
	}

	public List<PolicyAssign> assignedPolicyList(int userId) throws VehicleInsuranceException{
		List<PolicyAssign> assignedPolicyList = repo2.findByFieldOfficerId(userId);
		return assignedPolicyList;
	}

	public void registerSuccess(User user) {
		int id = (int) repo.count();
		user.setUserId(++id);
		repo.save(user);
	}

	public void rejectUser(int id) {
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			User user = opt.get();
			user.setStatus("Rejected");
			repo.saveAndFlush(user);
		}
	}

	public void approveUser(int id) {
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			User user = opt.get();
			user.setStatus("Approved");
			repo.saveAndFlush(user);
		}
	}

	public User getApprovedOfficer(User u) throws VehicleInsuranceException{
		User user = repo.findByUserNameAndPasswordAndStatus(u.getUserName(), u.getPassword(), "Approved");
		
		return user;
	}

	public User getPendingOfficer(User u) {
		User user = repo.findByUserNameAndPasswordAndStatus(u.getUserName(), u.getPassword(), null);
		return user;
	}

	public User getRejectedOfficer(User u) {
		User user = repo.findByStatus("Rejected");
		return user;
	}

	public User getUserByUsername(String userName) {
		return repo.findByUserName(userName);
	}

	public User getUserByPassword(String password) {
		return repo.findByPassword(password);
	}

	public void fieldOfficerValidation(Insurance insurance, int id) throws VehicleInsuranceException{
		Optional<Insurance> opt = repo1.findById(id);
		if (opt.isPresent()) {
			insurance = opt.get();
			insurance.setStatus("Validated by Field Officer");
			repo1.saveAndFlush(insurance);
		}else {
			throw new VehicleInsuranceException("No user found for "+id);
		}
	}

//	public void savedet(int policyAdminId, int policyId) {
//
//		int id = (int) repo2.count();
//		id++;
//		PolicyAssign p = new PolicyAssign();
//		p.setId(id);
//		p.setPolicyId(policyId);
//		p.setPolicyAdminId(policyAdminId);
//		repo2.saveAndFlush(p);
//
//		Insurance insurance = repo1.findById(policyId).get();
//		insurance.setStatus("Submitted to Policy Admin");
//		repo1.save(insurance);
//	}

	public List<Insurance> approvedList(String status) throws VehicleInsuranceException{
		List<Insurance> approvedList = repo1.findByStatusIs(status);
		return approvedList;
	}

	public List<Insurance> rejectedList(String status) throws VehicleInsuranceException{
		List<Insurance> rejectedList = repo1.findByStatusIs(status);
		return rejectedList;
	}

	public List<User> completeList() throws VehicleInsuranceException {
		List<User> list = repo.findAll();
		return list;
	}

	public String forgotId(User user) throws VehicleInsuranceException {
		List<User> list = completeList();
		for (User u : list) {
			if (u.getSecretQuestion().equals(user.getSecretQuestion())) {
				if (u.getSecretAnswer().equals(user.getSecretAnswer())) {
					return u.getUserName();
				}
			}
		}
		return "User Not Found";
	}

	int userId;
	public boolean resetPassword(User user) throws VehicleInsuranceException {
		List<User> list = completeList();
		for (User u : list) {
			if (user.getUserName().equals(u.getUserName())) {
				if (user.getEmail().equals(u.getEmail()) && user.getContactNumber().equals(u.getContactNumber())
						&& user.getSecretQuestion().equals(u.getSecretQuestion())
						&& user.getSecretAnswer().equals(u.getSecretAnswer())) {
					userId = u.getUserId();
					return true;
				}
			}
		}
		return false;
	}

	public void password(String newPassword) throws VehicleInsuranceException {
		List<User> list = completeList();
		for (User u : list) {
			if (userId == u.getUserId()) {
				u.setPassword(newPassword);
				repo.save(u);
			}
		}
	}
	public User getCustomerDetails(int id) {
		System.out.println(id);
		User user = repo.findById(id).get();
		return user;
	}

	public void updatecustomerdetails(User user)
	{
		user.setStatus("Approved");
		repo.save(user);
	
}
}
