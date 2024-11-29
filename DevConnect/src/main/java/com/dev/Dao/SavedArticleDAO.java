package com.dev.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.Model.Article;
import com.dev.Model.SavedArticle;
import com.dev.Model.SavedArticleKey;
import com.dev.Model.User;

@Repository
public interface SavedArticleDAO extends JpaRepository<SavedArticle, SavedArticleKey> {

	List<SavedArticle> findByUser(User user);
	Optional<SavedArticle> findByUserAndArticle(User user,Article article);
}	