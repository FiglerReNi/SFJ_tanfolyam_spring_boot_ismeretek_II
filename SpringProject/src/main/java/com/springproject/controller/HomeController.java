package com.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.domain.Tortenet;
import com.springproject.service.TortenetService;

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
	
	/*Egy objektumot kap vissza a böngésző (json-ben). Figyelnünk kell arra, hogy a foreig key miatt, ez így simán végtelen ciklusba fut és nem egy objektumot fog kiírni, 
	 * mert megtalálja a történetben a bloggert ami szintén egy objektum, amit kikeres, ebben megtalálja a storikat amik a bloggerhez tartoznak és így tovább
	 * Megoldás: az idegen kulcs alapjánál (mappedby) használjuk a @JasonBackReference annotációt*/
	@RequestMapping("/tortenet")
	public Tortenet tortenet() {
		return tortenetService.getEgyTortenet();
	}

	/*cím alaoján keresünk az adattáblában és megjelentjük - case sensitive!!!*/
	@RequestMapping(path = {"/title/{title}"})
	public Tortenet searchForTitle(@PathVariable(value="title") String title) throws Exception{
		return tortenetService.getSpecificTortenet(title);
	}
	
	//blogger alapján keresünk történeteket - case sensitive!!!
	@RequestMapping(path = {"/tortenetek/{name}"})
	public List<Tortenet> searchForTortenetekByBlogger(@PathVariable(value="name") String name) throws Exception{
		return tortenetService.getTortenetekByBlogger(name);
	}
	
	////Blogger alapján keres, de mindegy a kis és nagybetű
	@RequestMapping(path = {"/tortenetkereso/{name}"})
	public List<Tortenet> searchForTortenetKeresoByBlogger(@PathVariable(value="name") String name) throws Exception{
		return tortenetService.getTortenetKeresoByBlogger(name);
	}
	
}
