package com.security4.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.security4.entity.Role;
import com.security4.entity.User;

//Itt mondhatjuk meg a securitynek, hogy hogyan ismerjen fel egy usert, itt tudunk belenyúlni, hogy az adatbázisból vegye és hasonlítsa össze azt
//aki be akar jelentkezni
public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	
	//megadjuk a saját user objektumunkat, amit a beírt email alapján kikerestünk az adatbázisból
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	//ez a rész azzal foglalkozik, hogyan vegye ki a jogköröket, ki kell vennünk a user jogköreit és át kell adnunk a security számára
	//feldolgozható hashsetbe.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<Role> roles = user.getRoles();
		for(Role role :roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authorities;
	}
   
	// a jelszót a mi user objektumunkból vegye
	@Override
	public String getPassword() {
		   PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
	       return encoder.encode(user.getPassword());

	}

	// a felhasználót is a mi user objektumunkból vegye ki, a példában az emailt használjuk username-nek
	@Override
	public String getUsername() {
		return user.getEmail();
	}

	//lejárt e az account (nem vizsgáljuk így mindig true)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//lokkolva van-e
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//lejárt-e a felhasználó jelszava (bizonyos rendszerekben cserélni kell néha)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//engedélyezve van-e a fiók
	@Override
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
