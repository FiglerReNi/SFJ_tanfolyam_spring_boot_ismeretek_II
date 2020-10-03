package com.security1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RequestMapping -el egy egész osztályt is elláthatunk és a SecurityConfig->configure-ban állíthatunk hozzá jogot, hogy ki érje el.
 * Pl 
 * @RequestMapping("/admin") akkor minden ez alá kerül és a SecurityConfig->configure-ban beállíthatunk jögkört az admin/bármire 
 * .antMatchers("/admin/**")
	.hasRole("ADMIN");*/
//@RequestMapping("/admin")
@RestController
public class ApiController {

	@RequestMapping("/")
	public String index() {
		return "Főoldal";
	}
	
	/*Az oldalon van basic autentikáció, ha ott bejelentkezik valaki, utána a userhez rendelt szerepkörtől függ, hogy láthatja-e ezt az útvonalat
	 * mivel az application.propertiesben USER-t rendeltünk a felhasználóhoz, ezért ezt látni fogjuk.*/
	//@Secured(value = { "ROLE_USER" }) -ezt kiváltja a SecurityConfig->configure
	@RequestMapping("/stories")
	public String stories() {
		return "Stories";
	}
	
	//@Secured(value = { "ROLE_ADMIN" }) -ezt kiváltja a SecurityConfig->configure
	@RequestMapping("/delete")
	public String delete() {
		return "Delete";
	}
}
