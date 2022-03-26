package com.cts.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.vehicle.model.Policy;
import com.cts.vehicle.model.VehicleMakeDetails;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Integer>{

	
}
