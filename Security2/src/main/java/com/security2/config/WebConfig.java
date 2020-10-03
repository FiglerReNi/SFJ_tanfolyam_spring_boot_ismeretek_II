package com.security2.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Nézettel kapcsolatos dolgok állthatóak benne
public class WebConfig implements WebMvcConfigurer{
	
	/*A registry tárol kapcsolódási pontokat a nézetek és az útvonalak között
	 * jelenleg azért kell belenyúlnunk, hogy megadjuk, hol találja a login útvonalhoz a html-t*/
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		//hozzá adom az útvonalat és hogy hol van a html hozzá
		registry.addViewController("/login").setViewName("auth/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
	}

}