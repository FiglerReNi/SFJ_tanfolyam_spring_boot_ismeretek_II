package com.heroku.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.heroku.service.TortenetService;

@Controller
public class HomeController {
	private TortenetService tortenetService;
	
	@Autowired
	public void setTortenetService(TortenetService tortenetService) {
		this.tortenetService = tortenetService;
	}
	
	@RequestMapping("/tortenetek")
		public String tortenetek(Model model) {
			model.addAttribute("pageTitle", "Minden napra egy sztori");
			model.addAttribute("tortenetek", tortenetService.getTortenet());
			return "tortenetek";
		}
	
	@RequestMapping("/tortenet")
	public String tortenet(Model model) {
		model.addAttribute("pageTitle", "Minden napra egy sztori");
		model.addAttribute("tortenet", tortenetService.getEgyTortenet());
		return "tortenet";
	}

	/*cím alaoján keresünk az adattáblában és megjelentjük*/
	@RequestMapping(path = {"/title/{title}"})
	public String searchForTitle(@PathVariable(value="title") String title, Model model) throws Exception{
		model.addAttribute("tortenet", tortenetService.getSpecificTortenet(title));
		return "tortenet";
	}
	
}
