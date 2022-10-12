package com.shopping.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shopping.model.ERole;
import com.shopping.model.Role;

public interface RoleRepository extends MongoRepository<Role, String>{

	Optional<Role> findByName(ERole name);

}

