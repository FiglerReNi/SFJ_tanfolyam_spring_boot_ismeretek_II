package com.security.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Ez ahhoz kell, hogy meg tudjuk határozni, a különböző köntrollerekben melyik függvényt milyen jogosultságú felhasználó érhesse el. 
//a @Secured annotációhoz kell a securedEnabled = true
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

}
