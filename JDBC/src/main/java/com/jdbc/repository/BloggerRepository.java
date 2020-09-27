package com.jdbc.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.jdbc.domain.Blogger;

/*Az osztályt és az id adattípusát kell megadni*/
@Repository
public class BloggerRepository{

	//ezzel tudjuk használni a jdbc-t
	private JdbcTemplate jdbc;

	//konstrutor alapú injektálás
	@Autowired
	public BloggerRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	
	
}
