package com.gk.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gk.entity.User;
/**
 * 
 * @author Gangadhar
 *
 */
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	public Optional<User> findByUserName(String userName);

	public Optional<User> findByUserNameAndPassword(String userName, String password); 
	

}
