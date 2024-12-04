package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.CommentDAO;
import com.dev.Dao.TagDAO;
import com.dev.Model.Article;
import com.dev.Model.Comment;
import com.dev.Model.Tag;
import com.dev.Util.SlugUtil;
import com.dev.services.CountViewService;
import com.dev.services.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BlogContentController {

    private final ArticleDAO articleDAO;
    private final CountViewService countViewService;
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

            List<Article> relatedArticle = articleDAO.findAllRelatedArticles(tagDAO.findAllByArticleId(article.getArticleID()), article.getArticleID());

            List<Tag> tags = tagDAO.findAllByOrderByArticle(10);

            model.addAttribute("comment", new Comment());
            model.addAttribute("article", article);
            model.addAttribute("relatedArticle", relatedArticle);
            model.addAttribute("tags", tags);

            countViewService.incrementViews(article);
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

        String idComment = "";

        if (article.isPresent()) {
            comment.setId("com" + (latestCommentId + 1));
            comment.setUser(userPrincipal.getUser());
            comment.setArticle(article.get());
            Comment finalComment = commentDAO.save(comment);
            idComment = "#" + finalComment.getId();
        }
        return "redirect:/blog/" + title + idComment;
    }

}
