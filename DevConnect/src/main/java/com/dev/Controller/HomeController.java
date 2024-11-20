package com.dev.Controller;

import com.dev.Model.Article;
import com.dev.Repository.ArticleRepository;
import com.dev.Util.SlugUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    ArticleRepository articleRepository;

    public HomeController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Article> articles = articleRepository.findAllByOrderByCreateatDesc();
        articles.forEach(article -> article.setTitleSlug(SlugUtil.toSlug(article.getTitle())));
        model.addAttribute("articles", articles);
        return "home/index";
    }

    @GetMapping("/about")
    public String about() {
        return "home/about";
    }

}
