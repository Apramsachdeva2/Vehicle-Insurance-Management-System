package com.cts.vehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.vehicle.model.Policy;
import com.cts.vehicle.repository.PolicyRepository;

@Service
public class PolicyService {
	
	@Autowired
	PolicyRepository repo;
	
	public List<Policy> getAllPolicies() throws VehicleInsuranceException{
		List<Policy> policy= repo.findAll();
		return policy;
	}
	public Policy getPolicy(int id) {
		return repo.findById(id).get();
	}
	public void updatePolicy(Policy policy) {
		repo.saveAndFlush(policy);
	}
	public void savePolicy(Policy policy) {
		int id=(int)repo.count();
		policy.setPolicyId(++id);
		repo.save(policy);
	}
	public void deletePolicy(int id) {
		repo.deleteById(id);
	}

}
