package com.dev.Controller;

import com.dev.Model.Article;
import com.dev.Model.Comment;
import com.dev.Repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogContentController {

    ArticleRepository articleRepository;

    public BlogContentController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/blog-test")
    public String index(Model model) {
        List<Article> list = articleRepository.findAll();
        for (Article article : list) {
            for (Comment comment : article.getComments()) {
                comment.setCreateAtAsString(returnTime(comment.getCreateat().getTime()));
            }
        }
        model.addAttribute("article", list.get(0));
        return "blogContent";
    }

    @GetMapping("/blog/{title}")
    public String blog(Model model, @PathVariable String title) {
        List<Article> list = articleRepository.findAll();
        Optional<Article> article = list.stream().filter(a -> a.getTitle().equals(title)).findFirst();
        if (article.isPresent()) {
            model.addAttribute("article", article.get());
        } else {
            return "notFoundBlog";
        }
        return "blogContent";
    }

    private String returnTime(long duration) {
        Duration duration1 = Duration.between(Instant.ofEpochMilli(duration), Instant.now());
        Period period = Period.between(LocalDate.ofInstant(Instant.ofEpochMilli(duration), ZoneId.systemDefault()), LocalDate.now());
        if(period.getYears() > 1) {
            return period.getYears() + " năm trước";
        } else if (period.getMonths() > 1) {
            return period.getMonths() + " tháng trước";
        } else if (period.getDays() > 1) {
            return period.getDays() + " ngày trước";
        } else if (duration1.toHoursPart() > 1) {
            return duration1.toHoursPart() + " tiếng trước";
        } else if (duration1.toMinutesPart() > 1) {
            return duration1.toMinutesPart() + " phút trước";
        } else if (duration1.toSecondsPart() > 1) {
            return duration1.toSecondsPart() + " giây trước";
        } else
            return "0 giây trước";
    }
}
