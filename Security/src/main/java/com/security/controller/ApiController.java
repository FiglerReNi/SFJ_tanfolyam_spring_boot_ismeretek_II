package com.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@RequestMapping("/")
	public String index() {
		return "Főoldal";
	}
	
	/*Az oldalon van basic autentikáció, ha ott bejelentkezik valaki, utána a userhez rendelt szerepkörtől függ, hogy láthatja-e ezt az útvonalat
	 * mivel az application.propertiesben USER-t rendeltünk a felhasználóhoz, ezért ezt látni fogjuk.*/
	@Secured(value = { "ROLE_USER" })
	@RequestMapping("/stories")
	public String stories() {
		return "Stories";
	}
	
	@Secured(value = { "ROLE_ADMIN" })
	@RequestMapping("/delete")
	public String delete() {
		return "Delete";
	}
}
