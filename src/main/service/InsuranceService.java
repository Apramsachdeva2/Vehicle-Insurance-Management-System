package com.cts.vehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.User;
import com.cts.vehicle.repository.InsuranceRepository;
import com.cts.vehicle.repository.PolicyAssignRepository;

@Service
public class InsuranceService {

	@Autowired
	InsuranceRepository repo;
	@Autowired
	PolicyAssignRepository repo1;
	
	
	public List<Insurance> getAllInsuranceForPa(String status){
		return repo.findByStatusIs(status);
	}

	public List<Insurance> insuranceList() throws VehicleInsuranceException{
		List<Insurance> policyList = repo.findByStatusIs("Customer submitted");
		return policyList;

	}
	
	public Insurance getInsurance(int id) {
		return repo.findById(id).get();
	}

	public int display(Insurance insurance) {

	int id = (int) repo.count();
	insurance.setInsuranceId(++id);
	String date=insurance.getRegistrationDate();
	String year=date.substring(0,4);
	int addyear=Integer.parseInt(year)+1;
	String changeyear=String.valueOf(addyear);
	String remaining=date.substring(4);
	String newdate=changeyear+remaining;
	insurance.setExpireDate(newdate);
		insurance.setStatus("Customer submitted");
		repo.save(insurance);
		return id;
	}


	public Insurance viewpolicy(Insurance insurance, int id) throws VehicleInsuranceException{
		insurance = repo.findById(id).get();
		return insurance;

	}
	
	public void savedet(Insurance insurance) {
		repo.saveAndFlush(insurance);
	}

//	public void savedet(int fieldOfficerId, int policyId) {
//
//		int id = (int) repo1.count();
//		id++;
//		PolicyAssign p = new PolicyAssign();
//		p.setId(id);
//		p.setPolicyId(policyId);
//		p.setFieldOfficerId(fieldOfficerId);
//		repo1.saveAndFlush(p);
//
//		Insurance insurance = repo.findById(policyId).get();
//		insurance.setStatus("Submitted to Field Officer");
//		repo.save(insurance);
//	}
	public List<Insurance> getAllInsuranceById(int uid) throws VehicleInsuranceException{
		List<Insurance> insurance=repo.findAllByUserIdAndFeedBackTextIsNull(uid);
		return insurance;
	}
	
	public List<Insurance> getAllInsuranceByUser(int uid) throws VehicleInsuranceException{
		List<Insurance> insurance=repo.findAllByUserId(uid);
		return insurance;
	}
	
	public void updateInsuranceStatus(int insuranceId, String status) {
		Insurance insurance=repo.findById(insuranceId).get();
		insurance.setStatus(status);
		repo.saveAndFlush(insurance);
		
	}
	public String renewal(Insurance insurance) throws VehicleInsuranceException{
		Insurance insur=repo.findById(insurance.getInsuranceId()).get();
		String date=insur.getExpireDate();
		String year=date.substring(0,4);
		int addyear=Integer.parseInt(year)+1;
		String changeyear=String.valueOf(addyear);
		String remaining=date.substring(4);
		String newdate=changeyear+remaining;
		insur.setExpireDate(newdate);
		
		repo.save(insur);
		return newdate;
	     
	}
	public List<Insurance> AllInsurance() throws VehicleInsuranceException{
		List<Insurance> policyList = repo.findAll();
		return policyList;

	}
}
