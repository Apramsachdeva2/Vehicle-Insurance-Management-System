package com.cts.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.repository.PolicyAssignRepository;

@Service
public class PolicyAssignService {
	@Autowired
	PolicyAssignRepository repo;
	public List<PolicyAssign> getAllPoicyForFo(int id){
		return repo.findByFieldOfficerIdAndStatus(id,"Submitted to Field Officer");
	}
	public List<PolicyAssign> getAllPoicyForPa(int id){
		return repo.findByPolicyAdminIdAndStatus(id,"Submitted to Policy Admin");
	}
	
	public void assignFO(int policyId,int foId) {
		PolicyAssign assignment=repo.findByPolicyId(policyId);
		if(assignment==null)
		{
			assignment=new PolicyAssign();
			assignment.setPolicyId(policyId);
			assignment.setFieldOfficerId(foId);
			assignment.setStatus("Submitted to Field Officer");
			repo.saveAndFlush(assignment);
		}
		else {
			assignment.setFieldOfficerId(foId);
			assignment.setStatus("Submitted to Field Officer");
			repo.saveAndFlush(assignment);
		}
	}
	
	public void assignPA(int policyId,int paId) {
		PolicyAssign assignment=repo.findByPolicyId(policyId);
//		if(assignment==null)
//		{
//			assignment=new PolicyAssign();
//			assignment.setPolicyId(policyId);
//			assignment.setPolicyAdminId(paId);
//			assignment.setStatus("Submitted to Policy Admin");
//			repo.saveAndFlush(assignment);
//		}
//		else {
			assignment.setPolicyAdminId(paId);
			assignment.setStatus("Submitted to Policy Admin");
			repo.saveAndFlush(assignment);
//		}
	}
	
	public void updateAssignmentStatus(int policyId,String status) {
		PolicyAssign assignment=repo.findByPolicyId(policyId);
		assignment.setStatus(status);
		repo.saveAndFlush(assignment);
	}
	
}
