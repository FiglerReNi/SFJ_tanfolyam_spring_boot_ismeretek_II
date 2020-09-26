package com.springproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Tortenet;

@Repository
public interface TortenetRepository extends CrudRepository<Tortenet, Long>{

	/*A crudrepo findAll functionja egy Iterable típust ad vissza, de nekünk List kell, ezért a reponkban felül tudjuk írni, hogy mivel térjen vissza, mivel 
	 * az list rangsorban alatta található, konrétabb megfogalmazás*/
	List<Tortenet> findAll();

	/*Válogatás, a beépített crudrepository-s lekérdezéseken túl is van sok lehetőség*/
	//1-et akarunk visszaadni az elsőt amit megtalál dátum szerint a legfrissebbet;
	Tortenet findFirstByOrderByPostedDesc();

	//Cím alapján keresünk egyet
	Tortenet findByTitle(String title);
	
	//Blogger alapján keres
	List<Tortenet> findAllByBloggerNameOrderByPostedDesc(String name);

	//Blogger alapján keres, de mindegy a kis és nagybetű
	List<Tortenet> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);
}
