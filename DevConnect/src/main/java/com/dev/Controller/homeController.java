package com.dev.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dev.Dao.UserDao;
import com.dev.Model.SavedArticle;
import com.dev.Model.User;
import com.dev.Util.AuthUtil;
import com.dev.services.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.Dao.ArticleDAO;
import com.dev.Model.Article;

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
        if(authUtil.isUserAuthenticated()){
            System.out.println("is Authenticated");
            User user=userDAO.findById(userPrincipal.getUsername()).orElse(null);
            if(user!=null){
                Set<SavedArticle> savedArticles=user.getSavedArticles();
                Set<String> savedArticleIndex = new HashSet<>();
                savedArticles.forEach(savedArticle -> savedArticleIndex.add(savedArticle.getId().getArticle_id()));
                model.addAttribute("bookmarkArticleIds",savedArticleIndex);
            }
            List<Article> list= articleDAO.findAllByOrderByCreateatDesc();
            model.addAttribute("list", list);
            return "home/index";
        }
        System.out.println("is Not Authenticated");
        List<Article> list= articleDAO.findAllByOrderByCreateatDesc();
        model.addAttribute("list", list);
        return "home/index";
    }

}