package com.cts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.vehicle.model.PolicyAssign;

public interface PolicyAssignRepository extends JpaRepository<PolicyAssign,Integer>{

	
	public List<PolicyAssign> findByFieldOfficerId(Integer id);
	public List<PolicyAssign> findByPolicyAdminId(Integer id);
	public List<PolicyAssign> findByFieldOfficerIdAndStatus(Integer id,String status);
	public List<PolicyAssign> findByPolicyAdminIdAndStatus(Integer id,String status);
	public PolicyAssign findByPolicyId(Integer policyId);
}
