package com.springproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springproject.domain.Blogger;


/*Az osztályt és az id adattípusát kell megadni*/
@Repository
public interface BloggerRepository extends CrudRepository<Blogger, Long> {

}
