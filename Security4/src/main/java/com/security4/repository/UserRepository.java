package com.security4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.security4.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByEmail(String email);
}
