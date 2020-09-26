package com.heroku.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//ez jelzi hogy ez az osztály egy adatbázis táblát képvisel
@Entity(name="tortenetek")
public class Tortenet {
	//minden adattábla oszlopnak kell legyen itt egy változója
	//autoincrement
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//primary key
	@Id
	private Long id;
	@Column(name="cim")
	private String title;
	//@Column(length=1000)
	@Column(columnDefinition="TEXT")
	private String content;
	private Date posted;
	/*Foreign key létrehozása: OnetoMany kapcsolat, egy bloggerhez több story tartozhat és a story táblában lesz blogger_id, így ebben a kapcsolatban a 
	 * blogger a főnök azon az oldalon adom meg a kulcs adatait, itt csak jelzem, hogy ide tartozik*/
	@ManyToOne
	/*Az első tag mindig az osztályra utal, a második a változóra (több történet lehet egy bloggerhez).*/
	private Blogger blogger;
	
	private Tortenet() {}

	public Tortenet(String title, String content, Date posted, Blogger blogger) {
		this.title = title;
		this.content = content;
		this.posted = posted;
		this.blogger = blogger;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public void setBlogger(Blogger blogger) {
		this.blogger = blogger;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
