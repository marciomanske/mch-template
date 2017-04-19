package com.dminc.auth.repository;

import org.springframework.data.repository.CrudRepository;

import com.dminc.auth.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
 
    User findByUsername(String username);
    
}
