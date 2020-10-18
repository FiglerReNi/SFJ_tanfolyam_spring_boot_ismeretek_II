package com.security4.service;

import com.security4.entity.User;

public interface UserService {

	public User findByEmail(String email);
	
	public String registerUser(User user);

	public String userActivation(String code);
}
