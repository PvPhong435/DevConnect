package com.dev.services;

import com.dev.Dao.ArticleDAO;
import com.dev.Model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountViewService {

    private final ArticleDAO articleDAO;

    @Transactional
    public void incrementViews(String articleId) {
        Article article = articleDAO.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article " + articleId + " not found"));
        incrementViews(article);
    }

    @Transactional
    public void incrementViews(Article article) {
        article.incrementViews();
        articleDAO.save(article);
    }

}
