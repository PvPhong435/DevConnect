package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.CommentDAO;
import com.dev.Dao.TagDAO;
import com.dev.Model.Article;
import com.dev.Model.Comment;
import com.dev.Model.Tag;
import com.dev.Util.SlugUtil;
import com.dev.services.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BlogContentController {

    private final ArticleDAO articleDAO;
    private final TagDAO tagDAO;
    private final CommentDAO commentDAO;

    @GetMapping("/test")
    public String test() {
        return "blog/blog_test";
    }

    @GetMapping("/blog/{title}")
    public String blog(Model model, @PathVariable String title) {
        List<Article> list = articleDAO.findAll();
        Optional<Article> articleFound = list.stream()
                .filter(a -> SlugUtil.toSlug(a.getTitle()).equals(title))
                .findFirst();

        if (articleFound.isPresent()) {
            Article article = articleFound.get();
            for (Comment comment : article.getComments()) {
                comment.setCreateAtAsString(returnTime(comment.getCreateat().getTime()));
            }

            Set<Article> relatedArticle = new HashSet<>();
            for (Article article1 : list) {
                for (Tag tag : article.getTags()) {
                    if (article1.getTags().contains(tag) && !article1.getTitleSlug().equals(title)) {
                        relatedArticle.add(article1);
                        break;
                    }
                }
            }
            relatedArticle = relatedArticle.stream().sorted((o1, o2) -> o2.getCreateat().compareTo(o1.getCreateat())).collect(Collectors.toCollection(LinkedHashSet::new));

            List<Tag> tags = tagDAO.findAll();
            tags.sort((o1, o2) -> o2.getArticles().size() - o1.getArticles().size());
            tags = tags.subList(0, Math.min(tags.size(), 5));

            model.addAttribute("comment", new Comment());
            model.addAttribute("article", article);
            model.addAttribute("relatedArticle", relatedArticle);
            model.addAttribute("tags", tags);
        } else {
            return "redirect:home/index";
        }
        return "blog/blog_detail";
    }

    @PostMapping("/user/{title}/comment")
    public String comment(@AuthenticationPrincipal UserPrincipal userPrincipal, @ModelAttribute Comment comment, @PathVariable String title) {
        if (userPrincipal == null)
            return "Check/Login";
        int latestCommentId = commentDAO.findAll().stream()
                .map(c -> Integer.parseInt(c.getId().replaceAll("\\D", "")))
                .max(Comparator.naturalOrder()).orElse(0);

        Optional<Article> article = articleDAO.findAll().stream()
                .filter(a -> SlugUtil.toSlug(a.getTitle()).equals(title))
                .findFirst();
        if (article.isPresent()) {
            comment.setId("com" + (latestCommentId + 1));
            comment.setUser(userPrincipal.getUser());
            comment.setArticle(article.get());
            commentDAO.save(comment);
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
