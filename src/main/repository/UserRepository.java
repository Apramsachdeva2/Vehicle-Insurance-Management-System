package com.cts.vehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.vehicle.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findByStatusIsNull();
	public User findByUserNameAndPasswordAndStatus(String userName,String password,String status);
	public User findByUserName(String userName);
	public User findByPassword(String password);
	public User findByStatus(String status);
	public List<User> findByUserCategoryAndStatusIs(String userCategory,String status);
	
	
}
