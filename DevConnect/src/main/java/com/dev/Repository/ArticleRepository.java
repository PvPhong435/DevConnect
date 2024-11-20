package com.dev.Repository;

import com.dev.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

    List<Article> findAllByOrderByCreateatDesc();

}
