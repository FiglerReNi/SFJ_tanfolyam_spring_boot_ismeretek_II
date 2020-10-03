package com.security2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

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
	
}
