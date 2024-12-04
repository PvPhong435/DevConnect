package com.dev.Dao;

import com.dev.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String>{
	
	 Optional<User> findByUsername(String username);
}
