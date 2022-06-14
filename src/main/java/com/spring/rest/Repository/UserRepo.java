package com.spring.rest.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.rest.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	Optional<User> findByEmail(String email);
}
