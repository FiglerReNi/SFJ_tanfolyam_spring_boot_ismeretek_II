package com.security4.service;

import com.security4.entity.User;

public interface UserService {

	public User findByEmail(String email);
	
	public void registerUser(User user);
}
