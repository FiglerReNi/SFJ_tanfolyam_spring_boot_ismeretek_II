package com.springproject.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="bloggerek")
public class Blogger {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long id;
	private String name;
	private int age;
	//Kiiratásnál ezzel akadályozzuk meg a végtelen json létrejöttét.
	@JsonBackReference
	/*Foreign key másik fele, ez nem szerepel az adattáblában, csak a másik oldalon a párja, de itt is jelölni kell a kapcsolatot*/
	// Mivel több sztori lehet egy bloggerhez ezért kell a list, és jelzem hogy a blogger a fő  kapcsolatban a mappedBy-al
	@OneToMany(mappedBy = "blogger")
	private List<Tortenet> tortenet;
	
	private Blogger() {}

	public Blogger(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Tortenet> getTortenet() {
		return tortenet;
	}

	public void setTortenet(List<Tortenet> tortenet) {
		this.tortenet = tortenet;
	}
	
}
