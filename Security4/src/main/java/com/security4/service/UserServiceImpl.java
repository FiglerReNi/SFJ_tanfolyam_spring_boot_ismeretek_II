package com.security4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security4.entity.User;
import com.security4.repository.UserRepository;

//UserDetailsService ennek a segítségével mondjuk meg a securitynak, hogy a mi userdetails implementációnkat használja és ne a saját
//megoldását a beléptetéskor
@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	UserRepository userRepository;
	
	@Autowired
	public void setUserRepo(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	//a felületről kapja a username -t
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//keresse meg a beírt email alapján az adatbázisban a felhasználót
		User user = findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}

}
