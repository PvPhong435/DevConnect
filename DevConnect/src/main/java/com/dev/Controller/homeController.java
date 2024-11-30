package com.dev.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.Dao.ArticleDAO;
import com.dev.Model.Article;

@Controller
public class homeController {
	
	ArticleDAO articleDAO;
	
	
	
	public homeController(ArticleDAO articleDAO) {
		super();
		this.articleDAO = articleDAO;
	}

	@GetMapping("/")
	public String getHomePage(Model model) {
		List<Article> list= articleDAO.findAll();
		model.addAttribute("list",list);
		return "home/index";
	}
	@GetMapping("/blogs")
	public String getBlogs(Model model,@RequestParam Optional<String> search) {
		if(search.isPresent()) {
			List<Article> list=articleDAO.findAllByTitleContainingIgnoreCase(search, Sort.by(Sort.Direction.ASC,"articleID"));
			model.addAttribute("list", list);
			return "home/blogs";
		}
		List<Article> list= articleDAO.findAll();
		model.addAttribute("list",list);
		return "home/blogs";
	}
	
	
}
