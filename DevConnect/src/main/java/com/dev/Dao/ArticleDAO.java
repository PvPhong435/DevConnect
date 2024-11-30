package com.dev.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.Model.Article;

import java.util.List;

@Repository
public interface ArticleDAO extends JpaRepository<Article, String> {

    List<Article> findAllByOrderByCreateatDesc();
    List<Article> findAllByTitleContaining(String name);

}
