package com.cts.vehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.vehicle.model.Help;
import com.cts.vehicle.model.Insurance;
import com.cts.vehicle.repository.HelpRepository;



@Service
public class HelpService {
	
	@Autowired
	HelpRepository helpRepo;
	
	public void savehelp(Help help) {

		int id = (int) helpRepo.count();
		help.setRequestId(++id);
			helpRepo.save(help);

		}
	
	
	
	public List<Help> helprequestlist()
	{
		List<Help> helprequestlist = helpRepo.findAllByResolveRequestIsNull();
		return helprequestlist;
	}
	
	public Help requesthelp( int id) {
		return helpRepo.findById(id).get();

	}
	
	public List<Help> findAllById(int id){
		return helpRepo.findAllByUserId(id);
	}
	
	public void saveresolvehelp(Help help)
	{
		helpRepo.saveAndFlush(help);
	}
	
}
