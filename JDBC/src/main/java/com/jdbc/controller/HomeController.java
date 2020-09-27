package com.jdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.domain.Tortenet;
import com.jdbc.service.TortenetService;

@RestController
public class HomeController {
	private TortenetService tortenetService;
	
	@Autowired
	public void setTortenetService(TortenetService tortenetService) {
		this.tortenetService = tortenetService;
	}
	
	@RequestMapping("/tortenetek")
		public List<Tortenet> tortenetek() {
			return tortenetService.getTortenet();
		}

	/*cím alaoján keresünk az adattáblában és megjelentjük - case sensitive!!!*/
	@RequestMapping(path = {"/title/{title}"})
	public Tortenet searchForTitle(@PathVariable(value="title") String title) throws Exception{
		return tortenetService.getSpecificTortenet(title);
	}
	
	
	
}
