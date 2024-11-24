package com.dev.Controller;

import com.dev.Model.Article;
import com.dev.Model.Comment;
import com.dev.Model.Tag;
import com.dev.Repository.ArticleRepository;
import com.dev.Repository.CommentRepository;
import com.dev.Repository.TagRepository;
import com.dev.Util.SlugUtil;
import com.dev.services.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class BlogContentController {

    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;

    @GetMapping("/blog-test")
    public String index(Model model) {
        List<Article> list = articleRepository.findAll();
        for (Article article : list) {
            for (Comment comment : article.getComments()) {
                comment.setCreateAtAsString(returnTime(comment.getCreateat().getTime()));
            }
        }

        Set<Article> relatedArticle = new HashSet<>();
        for (int i = 0; i < list.size() && i < 3; i++) {
            Article article = list.get(i);
            for (Tag tag : list.get(0).getTags()) {
                if (article.getTags().contains(tag)) {
                    relatedArticle.add(article);
                    break;
                }
            }
        }

        List<Tag> tags = tagRepository.findAll();
        tags.sort((o1, o2) -> o2.getArticles().size() - o1.getArticles().size());
        tags = tags.subList(0, Math.min(tags.size(), 5));

        model.addAttribute("article", list.get(0));
        model.addAttribute("relatedArticle", relatedArticle);
        model.addAttribute("tags", tags);
        return "blog/blog_detail";
    }

    @GetMapping("/blog/{title}")
    public String blog(Model model, @PathVariable String title, @AuthenticationPrincipal UserPrincipal user) {
        List<Article> list = articleRepository.findAll();
        Optional<Article> articleFound = list.stream()
                .filter(a -> SlugUtil.toSlug(a.getTitle()).equals(title)).findFirst();

        if (articleFound.isPresent()) {
            Article article = articleFound.get();
            article.setTitleSlug(SlugUtil.toSlug(article.getTitle()));
            for (Comment comment : article.getComments()) {
                comment.setCreateAtAsString(returnTime(comment.getCreateat().getTime()));
            }

            Set<Article> relatedArticle = new HashSet<>();
            for (int i = 0; i < list.size() && i < 3; i++) {
                Article article1 = list.get(i);
                for (Tag tag : article.getTags()) {
                    if (article1.getTags().contains(tag)) {
                        relatedArticle.add(article1);
                        break;
                    }
                }
            }

            List<Tag> tags = tagRepository.findAll();
            tags.sort((o1, o2) -> o2.getArticles().size() - o1.getArticles().size());
            tags = tags.subList(0, Math.min(tags.size(), 5));

            model.addAttribute("comment", new Comment());
            model.addAttribute("article", article);
            model.addAttribute("relatedArticle", relatedArticle);
            model.addAttribute("tags", tags);
        } else {
            return "notFoundBlog";
        }
        return "blog/blog_detail";
    }

    @PostMapping("/blog/{title}/comment")
    public String comment(@AuthenticationPrincipal UserPrincipal userPrincipal, @ModelAttribute Comment comment, @PathVariable String title) {
        if (userPrincipal == null)
            throw new RuntimeException("User not logged in");
        int latestCommentId = commentRepository.findAll().stream().map(c -> Integer.parseInt(c.getId().replaceAll("\\D", ""))).max(Comparator.naturalOrder()).get();
        Optional<Article> article = articleRepository.findAll().stream()
                .filter(a -> SlugUtil.toSlug(a.getTitle()).equals(title)).findFirst();
        if (article.isPresent()) {
            comment.setId("com" + (latestCommentId + 1));
            comment.setUser(userPrincipal.getUser());
            comment.setArticle(article.get());
            commentRepository.save(comment);
        }
        return "redirect:/blog/" + title;
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
