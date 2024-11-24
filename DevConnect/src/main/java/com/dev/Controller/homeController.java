package com.dev.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dev.Dao.ArticleDAO;
import com.dev.Model.Article;

@Controller
public class homeController {
	
	ArticleDAO articleDAO;
	
	
	
	public homeController(ArticleDAO articleDAO) {
		super();
		this.articleDAO = articleDAO;
	}

	@GetMapping("/home/index")
	public String getHomePage(Model model) {
		List<Article> list= articleDAO.findAll();
		model.addAttribute("list",list);
		return "home/index";
	}
	
	
}
