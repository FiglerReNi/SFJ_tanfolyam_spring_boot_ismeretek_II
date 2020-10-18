package com.security4.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.security4.entity.User;
import com.security4.service.EmailService;
import com.security4.service.UserService;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//magát az interface injektálom be, mivel jelenleg csak egy osztály terjeszti ki, ezért tudni fogja melyik implementációból kell kivennie a függvények
	//megvalósítását. Ha több osztály implementálja, akkor a beinjektálásnál a @Qualifier annotációval mondjuk meg melyik osztályt akarjuk használni
	private UserService userService;
	
	//email küldéshez beinjektáljuk
	private EmailService emailService;
	
	@Autowired
	//@Qualifier("UserServiceImpl")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

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
		log.debug(user.getFullName());
		log.debug(user.getEmail());
		log.debug(user.getPassword());
		//emailt küldünk a regisztrációról
		userService.registerUser(user);
		emailService.sendMessage(user.getEmail(), user.getActivation(), user.getFullName());
		return "auth/login";
	}
	
	//a link amire az emailben kattint az aktivációhoz
	@RequestMapping(path= {"/activation/{code}"}, method = RequestMethod.GET)
	public String activation(@PathVariable(value="code") String code, HttpServletResponse response) {
		System.out.println("Teszt" + code);
		userService.userActivation(code);
		return "auth/login";		
	}
}
