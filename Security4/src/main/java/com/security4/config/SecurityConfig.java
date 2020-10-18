package com.security4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

//Ez ahhoz kell, hogy meg tudjuk határozni, a különböző köntrollerekben melyik függvényt milyen jogosultságú felhasználó érhesse el. 
//a @Secured annotációhoz kell a securedEnabled = true
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Szerver viselkedés konfigurálása
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception{
		httpSec
			.authorizeRequests()
			 	.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
			 	//regisztrációs felület
			 	.antMatchers("/registration").permitAll()
			 	.antMatchers("/activation/**").permitAll()
			 	//regisztrációt feldolgozó felület
			 	.antMatchers("/reg").permitAll()
				.antMatchers("/admin/**")
					.hasRole("ADMIN")
					//ezzel minden lekérdezést authentikációhoz kötünk, így csak a login és logout page-t fogja elérni a felhasználó
				.anyRequest().authenticated()
				.and()	
				.formLogin()
				//megadjuk, hogy ha kell a bejelentkező oldal az milyen útvonalon érhető el (ezzel felülírjuk az alapértelmezettet), ekkor
				//kell lennie egy login.html-nek 
					.loginPage("/login")
					.permitAll()
				.and()
				//hová irányítsuk a felhasználót ha kijelentkezik, itt most nem egy külön oldalt csináltunk ennek, 
				//hanem egy paraméter segítségével oldjuk meg.
				.logout()
					.logoutSuccessUrl("/login?logout")
					.permitAll();
			
	}		
}
