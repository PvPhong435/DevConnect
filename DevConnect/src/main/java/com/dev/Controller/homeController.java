package com.dev.Controller;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.UserDao;
import com.dev.Model.Article;
import com.dev.Util.AuthUtil;
import com.dev.services.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
    public String getHomePage(Model model, @AuthenticationPrincipal UserPrincipal userPrincipal) {
        authUtil.modelAddBookmarksIfAuthenticated(model, userPrincipal);
        List<Article> list= articleDAO.findAllByOrderByViewsDesc();
        model.addAttribute("list", list);
        return "home/index";
    }

}