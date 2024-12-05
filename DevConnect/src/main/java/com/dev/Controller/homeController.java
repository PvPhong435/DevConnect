package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.UserDao;
import com.dev.Model.Article;
import com.dev.Util.AuthUtil;
import com.dev.services.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class homeController {

    ArticleDAO articleDAO;
    AuthUtil authUtil;
    UserDao userDAO;

    public homeController(ArticleDAO articleDAO, AuthUtil authUtil, UserDao userDAO) {
        super();
        this.articleDAO = articleDAO;
        this.authUtil=authUtil;
        this.userDAO=userDAO;
    }

    @GetMapping({"/home/index", "/", ""})
    public String getHomePage(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam(name = "page", defaultValue = "1") int page) {
        authUtil.modelAddBookmarksIfAuthenticated(model, userPrincipal);

        int finalPage = Math.max(1, page);

        Page<Article> pages = articleDAO.findAll(PageRequest.of(finalPage - 1, 12, Sort.by(Sort.Direction.DESC, "views")));
        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        return "home/index";
    }

}