package com.security3.config;

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

	// Ennek a segítségével korlátlan felhasználót hozhatunk létre, a hitelesítést konfigurálhatjuk
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		//a jelszavakat titkosítani kell
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth
				// belső memóriában történő authentikációt hívom meg
				// állítok az authentikáción a következő userrel
			.inMemoryAuthentication()
				// itt állítok be usert
				.withUser("reni")
				// jelszó állítás
				.password(passwordEncoder.encode("pass"))
				// jogör beállítása
				.roles("USER")
				// második user beállítása
			.and()
				.withUser("sfj")
				.password(passwordEncoder.encode("sfj"))
				.roles("ADMIN");

	}
	
	//Szerver viselkedés konfigurálása
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception{
		httpSec
			.authorizeRequests()
			 	.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
			 	//regisztrációs felület
			 	.antMatchers("/registration").permitAll()
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
