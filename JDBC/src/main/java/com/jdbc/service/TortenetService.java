package com.jdbc.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdbc.domain.Blogger;
import com.jdbc.domain.Tortenet;
import com.jdbc.repository.BloggerRepository;
import com.jdbc.repository.TortenetRepository;

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

	public Tortenet getSpecificTortenet(String title) {
		return tortenetRepo.findByTitle(title);		
	}

}
