package com.dev.Util;

import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    public boolean isUserAuthenticated(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication!=null&&authentication.isAuthenticated()&&!(authentication instanceof AnonymousAuthenticationToken);
    }
}
