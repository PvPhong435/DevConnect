package com.dev.Dao;

import com.dev.Model.Article;
import com.dev.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArticleDAO extends JpaRepository<Article, String> {

    @Query("select distinct a from Article a " +
            "join a.articleTags at " +
            "join at.tag t " +
            "where t.id = :tagId " +
            "order by a.createat desc")
    List<Article> findAllByTagId(@Param("tagId") String tagId);

    List<Article> findAllByOrderByViewsDesc();

    List<Article> findAllByTitleContainingIgnoreCase(String name);

    @Query("select distinct a from Article a " +
            "join a.articleTags at " +
            "join at.tag t " +
            "where t in :tags and a.articleID <> :articleId " +
            "order by a.createat desc")
    List<Article> findAllRelatedArticles(@Param("tags") Collection<Tag> tags, @Param("articleId") String articleId);

}
