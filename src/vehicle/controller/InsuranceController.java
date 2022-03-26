package com.cts.vehicle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.model.PolicyAssign;
import com.cts.vehicle.model.User;
import com.cts.vehicle.repository.InsuranceRepository;
import com.cts.vehicle.repository.PolicyAssignRepository;
import com.cts.vehicle.service.InsuranceService;
import com.cts.vehicle.service.PolicyAssignService;
import com.cts.vehicle.service.VehicleInsuranceException;

@Controller
//@SessionAttributes("myAssignments")
public class InsuranceController {

	
	@Autowired
	InsuranceService service;

	
	@Autowired
	PolicyAssignService pas;
	
	@PostMapping(value="/saveInsurance")
    public String display(@ModelAttribute("command") Insurance insurance,ModelMap model) throws VehicleInsuranceException
    {
        int id=service.display(insurance);
        List<Insurance> lst = service.getAllInsuranceById(insurance.getUserId());
		model.put("insuranceListt", lst);
		model.put("insurancemsg","Your new insurance policy is submitted for validation,Your insurance id is:"+id);
        return "customer";
    }
	
	
	
	@RequestMapping("/viewpolicy/{id}")
	public String viewpolicy(@ModelAttribute("command") Insurance insurance,@PathVariable("id") int id,ModelMap model) throws VehicleInsuranceException
	{
		 insurance=service.viewpolicy(insurance, id);
	     model.put("firstName", insurance.getFirstName());
	     model.put("lastName", insurance.getLastName());
	     model.put("address", insurance.getAddress());
	     model.put("registrationnumber", insurance.getVehicleRegistrationNumber());
	     model.put("mobilenumber", insurance.getMobileNumber());
	     model.put("email", insurance.getEmailId());
	     model.put("cityofregistration", insurance.getCityOfRegistration());
	     model.put("registrationdate", insurance.getRegistrationDate());
	     model.put("make", insurance.getMake());
	     model.put("model", insurance.getModel());	
	     model.put("variant",insurance.getVariant());
	     model.put("year", insurance.getManufacturingYear());
	     model.put("fueltype", insurance.getFuelType());
	     model.put("policyId", insurance.getPolicyId());
	    
		return "policylist";
		
	}
	
	@ModelAttribute("insuranceList")
	public List<Insurance> insuranceList1(ModelMap map) throws VehicleInsuranceException{
		List<Insurance> policyList=new ArrayList<Insurance>();
		if(map.get("u")!=null) {
		policyList=service.getAllInsuranceById(((User)map.get("u")).getUserId());
		}
		return policyList;
		
	}
	
		
	
	@RequestMapping(value="/renewal")
	public String renewal(@ModelAttribute("renewal") Insurance insurance ,ModelMap map) throws VehicleInsuranceException
	{
		String expirydate=service.renewal(insurance);
		map.put("expirydate", "Your insurance is renewed successfully and your new renewal date is "+expirydate);
		
		return "renewal";
	}
	@RequestMapping(value="/renewalpage")
	public String renewalpage(@ModelAttribute("renewal") Insurance insurance)
	{
		
		return "renewal";
	}
	
	@RequestMapping(value="/insurance")
	public String insurancepage(@ModelAttribute("command") Insurance insurance)
	{
		
		return "insurance";
	}
	
	@RequestMapping(value="/customerpage")
	public String customerpage(@ModelAttribute("command") Insurance insurance) {
		return "customer";
	}
	
}
