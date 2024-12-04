package com.dev.Dao;

import com.dev.Model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagDAO extends JpaRepository<Tag, String> {

    List<Tag> findAllByOrderById();

    List<Tag> findAllByIdContainingIgnoreCase(String id);

    @Query("select t from Tag t " +
            "left join t.articleTags at " +
            "left join at.article a " +
            "group by t " +
            "having count(at) > 0 " +
            "order by count(at) desc, sum(at.article.views) desc " +
            "limit :limit")
    List<Tag> findAllIfHasAtLeastOneArticleAndOrderByArticle(@Param("limit") int limit);

    @Query("select distinct t from Tag t " +
            "join t.articleTags at " +
            "join at.article a " +
            "where a.articleID = :articleId")
    List<Tag> findAllByArticleId(@Param("articleId") String articleId);

    @Query("select t from Tag t " +
            "join t.articleTags at " +
            "group by t " +
            "order by count(at) desc " +
            "limit :limit")
    List<Tag> findAllByOrderByArticle(@Param("limit") int limit);

}
