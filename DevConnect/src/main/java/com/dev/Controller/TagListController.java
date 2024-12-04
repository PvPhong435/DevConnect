package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.TagDAO;
import com.dev.Model.Tag;
import com.dev.Util.AuthUtil;
import com.dev.services.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TagListController {

    private final TagDAO tagDAO;
    private final ArticleDAO articleDAO;
    private final AuthUtil authUtil;

    @GetMapping("/tags")
    public String index(Model model) {
        model.addAttribute("tags", tagDAO.findAllByOrderById());
        model.addAttribute("tagspopular", tagDAO.findAllIfHasAtLeastOneArticleAndOrderByArticle(10));
        return "tag/taglist";
    }

    @GetMapping("/tags/{name}")
    public String tag(@PathVariable String name, Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        name = name.startsWith("#") ? name : "#" + name;
        Optional<Tag> optionalTag = tagDAO.findById(name);
        if (optionalTag.isPresent()) {
            authUtil.modelAddBookmarksIfAuthenticated(model, userPrincipal);
            model.addAttribute("tag", optionalTag.get());
            model.addAttribute("articles", articleDAO.findAllByTagId(name));
        } else {
            return "redirect:/tags";
        }
        return "tag/each";
    }

}
