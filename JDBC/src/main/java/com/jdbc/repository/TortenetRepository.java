package com.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.jdbc.domain.Tortenet;

@Repository
public class TortenetRepository{
	
	//ezzel tudjuk használni a jdbc-t
	private JdbcTemplate jdbc;
	
	//konstrutor alapú injektálás
	@Autowired
	public TortenetRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<Tortenet> findAll(){
		String sql = "SELECT * FROM tortenetek ORDER BY posted DESC";
		/*második paraméter azt mondja meg mi alapján állítsa össze az objektumokat:
		 * lambda expression-nel két paramétert vár, az egyik a result set amit a lekérdezésből a jdbc visszaad és egy i váltzó, 
		 * majd ebből állítson össze egy történet objektumot, minden sorból*/	
		return jdbc.query(sql, (rs, i) -> new Tortenet(
				rs.getLong("id"),
				rs.getString("title"),
				rs.getString("content"),
				rs.getDate("posted"),
				rs.getLong("blogger_id")							
				));
	}

	public Tortenet findByTitle(String title) {
		String sql = "SELECT * FROM tortenetek WHERE title = ? ORDER BY posted";
		//paraméterrel és anonim osztállyal	
		return jdbc.queryForObject(
				sql,
				//paraméterek átadása
				new Object[] {title},
				//az előbb lamda-val csinált rész
				new RowMapper<Tortenet>() {
					@Override
					public Tortenet mapRow(ResultSet rs, int rowNum) throws SQLException {
						Tortenet tortenet = new Tortenet();
						tortenet.setId(rs.getLong("id"));
						tortenet.setTitle(rs.getString("title"));
						tortenet.setContent(rs.getString("content"));
						tortenet.setPosted(rs.getDate("posted"));
						tortenet.setBlogger_id(rs.getLong("blogger_id"));
						return tortenet;
					}				
				}
				);
	}
	


/*
	Tortenet findFirstByOrderByPostedDesc();

	Tortenet findByTitle(String title);

	List<Tortenet> findAllByBloggerNameOrderByPostedDesc(String name);

	List<Tortenet> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);*/

}
