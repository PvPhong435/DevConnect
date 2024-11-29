package com.dev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.Model.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {

}
