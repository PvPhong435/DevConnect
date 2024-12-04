package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.TagDAO;
import com.dev.Model.Article;
import com.dev.Model.ArticleTag;
import com.dev.Model.Tag;
import com.dev.Util.AuthUtil;
import com.dev.services.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final TagDAO tagDAO;
    private final ArticleDAO articleDAO;
    private final AuthUtil authUtil;

    @GetMapping("/search")
    public String search(Model model, @RequestParam("q") String search, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<Article> articlesFound = new ArrayList<>();
        String message;
        if (search.startsWith("#")) {
            List<Tag> listTagsFound = tagDAO.findAllByIdContainingIgnoreCase(search);
            if (!listTagsFound.isEmpty()) {
                Optional<Tag> tag = listTagsFound.stream().filter(t -> t.getId().equals(search)).findFirst();
                if (tag.isPresent()) {
                    message = "Tìm thấy các bài viết của " + search;
                    articlesFound = tag.get().getArticleTags().stream().map(ArticleTag::getArticle).collect(Collectors.toList());
                } else {
                    message = "Tìm thấy các bài viết liên quan " + search;
                    articlesFound = listTagsFound.stream().flatMap(tagFound -> tagFound.getArticleTags().stream()).map(ArticleTag::getArticle).collect(Collectors.toList());
                }
            } else
                message = "Không tìm thấy tag liên quan " + search;
        } else {
            message = "Tìm thấy các bài viết liên quan: " + search;
            articlesFound = articleDAO.findAllByTitleContainingIgnoreCase(search);
        }

        if (articlesFound.isEmpty()) {
            message = "Không tìm thấy kết quả liên quan: " + search;
        }

        articlesFound.sort(Comparator.comparing(Article::getCreateat).reversed());

        authUtil.modelAddBookmarksIfAuthenticated(model, userPrincipal);
        model.addAttribute("articles", articlesFound);
        model.addAttribute("message", message);
        return "blog/search";
    }

}
