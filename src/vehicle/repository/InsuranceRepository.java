package com.cts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.vehicle.model.Insurance;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance,Integer> {
	
	
	public List<Insurance> findByStatusIs(String status);
	public List<Insurance> findAllByUserIdAndFeedBackTextIsNull(int uid);
	public List<Insurance> findAllByUserId(int uid);
	

}
