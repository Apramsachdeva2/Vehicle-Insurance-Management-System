package com.cts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.vehicle.model.Help;



@Repository
public interface HelpRepository extends JpaRepository<Help,Integer>{
	public List<Help> findAllByUserId(int user_id);
	public List<Help> findAllByResolveRequestIsNull();
}
