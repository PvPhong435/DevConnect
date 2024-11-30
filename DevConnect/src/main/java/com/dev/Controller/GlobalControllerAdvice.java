package com.dev.Controller;

import com.dev.services.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("auth")
    public UserPrincipal getUserPrincipal(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userPrincipal;
    }

}
