package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.TagDAO;
import com.dev.Model.Article;
import com.dev.Model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final TagDAO tagDAO;
    private final ArticleDAO articleDAO;

    @GetMapping("/search")
    public String search(Model model, @RequestParam("q") String search) {
        List<Article> articlesFound = new ArrayList<>();
        String message;
        if (search.startsWith("#")) {
            List<Tag> tags = tagDAO.findAll();
            List<Tag> listTagsFound = tags.stream().filter(t -> t.getTagByName().contains(search)).toList();
            if (!listTagsFound.isEmpty()) {
                Optional<Tag> tag = listTagsFound.stream().filter(t -> t.getTagByName().equals(search)).findFirst();
                if (tag.isPresent()) {
                    message = "Tìm thấy các bài viết của #" + search;
                    articlesFound = tag.get().getArticles();
                } else {
                    message = "Tìm thấy các bài viết liên quan #" + search;
                    articlesFound = listTagsFound.stream().flatMap(tagFound -> tagFound.getArticles().stream()).collect(Collectors.toList());
                }
            } else
                message = "Không tìm thấy tag liên quan #" + search;
        } else {
            message = "Tìm thấy các bài viết liên quan: " + search;
            articlesFound = articleDAO.findAllByTitleContaining(search);
        }

        if (articlesFound.isEmpty()) {
            message = "Không tìm thấy kết quả liên quan: " + search;
        }

        articlesFound.sort((o1, o2) -> o2.getCreateat().compareTo(o1.getCreateat()));
        model.addAttribute("articles", articlesFound);
        model.addAttribute("message", message);
        return "blog/search";
    }

}
