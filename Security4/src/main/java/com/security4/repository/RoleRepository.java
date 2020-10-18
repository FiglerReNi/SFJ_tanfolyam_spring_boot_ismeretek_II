package com.security4.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.security4.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role);
}
