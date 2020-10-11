package com.security3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.security3.entity.User;
import com.security3.service.EmailService;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//email küldéshez beinjektáljuk
	private EmailService emailService;
	
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

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
	
	@PostMapping("/reg")
	//Ezzel veszem át a frontendről jövő adatot: @ModelAttribute
	public String reg(@ModelAttribute User user) {
		//itt bármit csinálhtunk a bejövő adattal, elmenthetjük pl egy adatbázisba stb
		//konzolra sose írunk ki jelszót, ez csak példa miatt van
		System.out.println("ÚJ USER");
		log.info("Új user!");
		log.debug(user.getEmail());
		log.debug(user.getPassword());
		//emailt küldünk a regisztrációról
		emailService.sendMessage(user.getEmail());
		return "auth/login";
	}
	
}
