package com.springproject.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.domain.Blogger;
import com.springproject.domain.Tortenet;
import com.springproject.repository.BloggerRepository;
import com.springproject.repository.TortenetRepository;

//Ennek a segítségével használjuk a repository eszközeit a tényleges adat összeállításhoz, amit aztán átadunk a kontrollernek, hogy jelenítse meg
@Service
public class TortenetService {

	/*Adat kiolvasása adatbázisból. Kell hozzá az adott osztály repositoryja, hiszen ez a híd az adatbázis felé az entity-n keresztül
	 * Dependency Injectionnal adom meg*/
	
	private TortenetRepository tortenetRepo;
	private BloggerRepository bloggerRepo;
	
	@Autowired
	public void setTortenet(TortenetRepository tortenetRepo) {
		this.tortenetRepo = tortenetRepo;
	}
	
	@Autowired
	public void setBloggerRepo(BloggerRepository bloggerRepo) {
		this.bloggerRepo = bloggerRepo;
	}
	
	/*A repo findAll functionja egy Iterable típust ad vissza, de nekünk List kell, ezért a reponkban felül tudjuk írni, hogy mivel térjen vissza, mivel 
	 * az list a szűkebb adattípus*/
	public List<Tortenet> getTortenet(){
		return tortenetRepo.findAll();		
	}
	
	public Tortenet getEgyTortenet() {
		return tortenetRepo.findFirstByOrderByPostedDesc();
	}


	public Tortenet getSpecificTortenet(String title) {
		return tortenetRepo.findByTitle(title);
		
	}

	public List<Tortenet> getTortenetekByBlogger(String name) {
			return tortenetRepo.findAllByBloggerNameOrderByPostedDesc(name);
	}

	public List<Tortenet> getTortenetKeresoByBlogger(String name) {
		return tortenetRepo.findAllByBloggerNameIgnoreCaseOrderByPostedDesc(name);
	}
}
