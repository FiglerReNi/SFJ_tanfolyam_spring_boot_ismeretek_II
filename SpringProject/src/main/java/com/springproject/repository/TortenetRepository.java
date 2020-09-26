package com.springproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

	//A VÁLTOZAT -JPA
	//Cím alapján keresünk egyet
	//Tortenet findByTitle(String title);
	
	//B VÁLTOZAT -NATIV SQL
		//a. paraméter megadás ?-el
	//@Query(value = "SELECT * FROM tortenetek WHERE cim = ?1 LIMIT 1", nativeQuery=true)
	//Tortenet findByTitle(String title);
		//b. paraméter megadás name-el
	//@Query(value = "SELECT * FROM tortenetek WHERE cim = :cim LIMIT 1", nativeQuery=true)
	//Tortenet findByTitle(@Param("cim") String title);
	
	//C VÁLTOZAT JPQL - objetumként tekint a visszatérő eredményre, nem sorként, from után az entity neve kell, de a where feltételbe
	//már az objektum osztályváltozó neve
	@Query(value="SELECT t FROM tortenetek t WHERE t.title = :cim")
	Tortenet findByTitle(@Param("cim") String title);
	
	//Blogger alapján keres
	List<Tortenet> findAllByBloggerNameOrderByPostedDesc(String name);

	//Blogger alapján keres, de mindegy a kis és nagybetű
	List<Tortenet> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);
}
