package com.security4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security4.entity.Role;
import com.security4.entity.User;
import com.security4.repository.RoleRepository;
import com.security4.repository.UserRepository;

//UserDetailsService ennek a segítségével mondjuk meg a securitynak, hogy a mi userdetails implementációnkat használja és ne a saját
//megoldását a beléptetéskor
@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	//minden regisztrálónak ezt a jogot adjuk alapból
	private final String USER_ROLE = "USER";

	UserRepository userRepository;
	
	RoleRepository roleRepository;
	
	@Autowired
	public void setUserRepo(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
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


	@Override
	public void registerUser(User user) {
		//megnézzük, hogy a szerepkörök között szerepel-e már a USER (minden regisztrált felhasználónak ezt akarjuk megadni)
		Role userRole = roleRepository.findByRole(USER_ROLE);		
		if(userRole != null) {
			//ha van már a szerepkör adatbázisban, aor kikeresem és a már megévőt állítom be az új userhez
			//user.getRoles() --> kikérem a már meglévő rolejait, ami most az üres hashset lesz és hozzáadok
			user.getRoles().add(userRole);
		}else {
			//be kell állítanunk a usernek szerepkört, mert ez nem jön a felületről, de ez csak akkor jó megoldás, ha a szerepkör még nincs az adatbázisban
			//mert ezzel azt is létrehozza
			//A változat
			user.addRole(USER_ROLE);
			//B változat
			//user.getRoles().add(new Role(USER_ROLE));
		}		
		//elmentjük a usert
		userRepository.save(user);
		
	}

}
