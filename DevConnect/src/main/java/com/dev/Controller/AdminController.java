package com.dev.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String admin(){return "admin/dashboard";}

    @GetMapping("/admin/posts")
    public String post(){return "admin/posts";}
    // Day la category
    @GetMapping("/admin/categories")
    public String category(){return "admin/category";}

    @GetMapping("/admin/user-management")
    public String user(){return "admin/userManage";}

    @GetMapping("/admin/posts/create")
    public String createPost() {
        return "admin/postCreate"; // Tạo view mới cho việc tạo bài viết
    }

}
