package com.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
				// állítok a z authentikáción a következő userrel
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
		/*A http security felügyeli a http forgalmat a szerver és a felhasználó között
		 * Meghatározhatjuk, hogy mihez adunk hozzáférést, például mp3 fájlokhoz nem stb*/
		/*Két módszer van:
		 * 	- engedélyezve van minden és néhány dologra hozunk szabályt
		 * 	- előbb tiltunk mindent és fokozatosan nyitunk ki hozzáféréseet (ez a jobb)*/
		httpSec
		//bizonyos requesteket engedélyezünk(első módszerre példa)
			.authorizeRequests()
			//gyökércsatornára irányuló GET lekérdezések figyelése
				.antMatchers(HttpMethod.GET, "/")
				//a fenti útvonalra érkező minden requestet engedélyezünk
					.permitAll()
				//jogosultság figyelés, az alábbi útvonalnál fogyelje az Admin jogosultságot(ez kiváltja az apiControlleres @Secured figyelést)
				.antMatchers("/delete")
					.hasRole("ADMIN")
				//mindent figyeljen ami az admin/bármi (mp3, pdf, html stb) útvonalra irányul
				.antMatchers("/admin/**")
					.hasRole("ADMIN")
			//a bejelentkező felületet elérhetővé kell tenni (ha belenyúltunk itt az engedélyekbe, nem lesz automatikus mint korábban)
			.and()
			.formLogin()
				.permitAll();
			
		
				
	}		
}
