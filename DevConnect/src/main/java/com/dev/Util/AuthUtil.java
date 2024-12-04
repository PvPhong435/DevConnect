package com.dev.Util;

import com.dev.Dao.UserDao;
import com.dev.Model.SavedArticle;
import com.dev.Model.User;
import com.dev.services.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserDao userDao;

    public boolean isUserAuthenticated(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication!=null&&authentication.isAuthenticated()&&!(authentication instanceof AnonymousAuthenticationToken);
    }

    public void modelAddBookmarksIfAuthenticated(Model model, UserPrincipal userPrincipal) {
        if(isUserAuthenticated()){
            System.out.println("is Authenticated");
            User user=userDao.findById(userPrincipal.getUsername()).orElse(null);
            if(user!=null){
                Set<SavedArticle> savedArticles=user.getSavedArticles();
                Set<String> savedArticleIndex = savedArticles.stream().map(savedArticle -> savedArticle.getId().getArticle_id()).collect(Collectors.toSet());
                model.addAttribute("bookmarkArticleIds",savedArticleIndex);
            }
        } else
            System.out.println("is Not Authenticated");
    }
}
