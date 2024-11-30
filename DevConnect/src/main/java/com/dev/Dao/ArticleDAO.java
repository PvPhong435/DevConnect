package com.dev.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.Model.Article;
@Repository
public interface ArticleDAO extends JpaRepository<Article, String> {
	List<Article> findAllByTitleContainingIgnoreCase(Optional<String> input,Sort sort);
}
