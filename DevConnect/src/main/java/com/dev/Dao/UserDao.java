package com.dev.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dev.Model.User;


public interface UserDao extends JpaRepository<User, String>{
	
	public User findByUsername(String username);
	
	public List<User> findByRoleLike(String role);
	
	public List<User> findByFullnameLike(String name);
	
	
}
