package com.dev.Dao;

import com.dev.Model.Article;
import com.dev.Model.SavedArticle;
import com.dev.Model.SavedArticleKey;
import com.dev.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedArticleDAO extends JpaRepository<SavedArticle, SavedArticleKey> {

	@Query("select sa from SavedArticle sa " +
			"where sa.user = :user " +
			"order by sa.save_date desc")
	List<SavedArticle> findByUser(@Param("user") User user);
	Optional<SavedArticle> findByUserAndArticle(User user,Article article);
}	
