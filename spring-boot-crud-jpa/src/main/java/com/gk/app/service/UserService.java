package com.gk.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gk.dao.UserRepo;
import com.gk.entity.User;

@Repository
public class UserService {
	@Autowired
	private UserRepo userRepo;

	public boolean save(User user) {
		if (userRepo.save(user) != null)
			return true;
		return false;
	}

	public boolean validateUser(String userName,String password) {
		if (userRepo.findByUserNameAndPassword(userName,password).isPresent()) {
			return true;
		}
		return false;
	}
	
	public Optional<User> getUser(String userName){
		return userRepo.findByUserName(userName);
	}
}
