package com.security2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.security2.entity.User;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/") //endpoint
	public String home() {
		return "index"; //view
	}
	
	@RequestMapping("/bloggers")
	public String bloggers(){
		return "bloggers";
	}
	
	
	@RequestMapping("/stories")
	public String stories(){
		return "stories";
	}
	
	//Amikor jön egy kérés erre az útvonlra akkor a html megjelenítésekor már átadunk egy új user objektumot, mert az adatbekéréskor ehhez kérünk adatokat,
	// ezt töltjük fel a form adataival
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	//ide jön be a regisztrációs form, csak post-ot fogad, vagyis ha csak úgy beírom a böngészőbe hogy /reg, de nem formról jön az nem számít
	//@RequestMapping(value="/reg", method=RequestMethod.POST)
	//rövidebben
	@PostMapping("/reg")
	//Ezzel veszem át a frontendről jövő adatot: @ModelAttribute
	public String reg(@ModelAttribute User user) {
		//itt bármit csinálhtunk a bejövő adattal, elmenthetjük pl egy adatbázisba stb
		//konzolra sose írunk ki jelszót, ez csak példa miatt van
		System.out.println("ÚJ USER");
		log.info("Új user!");
		log.debug(user.getUsername());
		log.debug(user.getPassword());
		return "auth/login";
	}
	
}
