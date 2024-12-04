package com.dev.Controller;

import com.dev.Util.AuthUtil;
import com.dev.services.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final AuthUtil authUtil;

    @ModelAttribute("auth")
    public UserPrincipal getUserPrincipal(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return userPrincipal;
    }

    @ModelAttribute("isAuthenticated")
    public boolean isAuthenticated() {
        return authUtil.isUserAuthenticated();
    }

}
