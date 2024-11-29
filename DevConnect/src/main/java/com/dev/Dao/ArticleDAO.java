package com.dev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.Model.Article;
@Repository
public interface ArticleDAO extends JpaRepository<Article, String> {
	
}