package com.dev.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.Model.User;

public interface UserDao extends JpaRepository<User, String>{
	
	 Optional<User> findByUsername(String username);
}
