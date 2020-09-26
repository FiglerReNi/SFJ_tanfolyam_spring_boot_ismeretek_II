package com.heroku.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroku.domain.Blogger;
import com.heroku.domain.Tortenet;
import com.heroku.repository.BloggerRepository;
import com.heroku.repository.TortenetRepository;

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
		/*minden oldalfrissítéskor le fog futni az init és létrehoz egy új példányt ---> csak egyszer szeretnénk*/
		//init();
		return tortenetRepo.findAll();		
	}
	
	public Tortenet getEgyTortenet() {
		return tortenetRepo.findFirstByOrderByPostedDesc();
	}

	/*Új Blogger létrehozása és mentése*/
	//ha csak egyszer szeretnénk hogy valami lefusson a service létrejöttekor (szerver indításakor megtörténik)
	@PostConstruct
	public void init() {
		Blogger blogger = new Blogger("Figler Reni", 25);
		bloggerRepo.save(blogger);
		Tortenet tortenet = new Tortenet("Cím2", "Történet2", new Date(), blogger);
		tortenetRepo.save(tortenet);
	}

	public Tortenet getSpecificTortenet(String title) {
		return tortenetRepo.findByTitle(title);
		
	}
}
