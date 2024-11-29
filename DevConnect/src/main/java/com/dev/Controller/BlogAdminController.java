package com.dev.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.CategoryDAO;
import com.dev.Model.Article;
import com.dev.Model.Category;


@Controller
public class BlogAdminController {
	
	@Autowired
	CategoryDAO categoryDao;
	ArticleDAO articleDao;
	
	@RequestMapping("/admin/blog")
	public String getAllBlog(Model model)
	{
		List<Category> categories=categoryDao.findAll();
		List<Article> articles=articleDao.findAll();
		model.addAttribute("categories", categories);
		return "PostAdmin";
	}
	
	@RequestMapping("/admin/blog/remove")
	public String removeBlog(Model model)
	{
		
		return "";
	}
	
	@RequestMapping("/admin/blog/edit/{id}")
	public String editBlog(Model model,@RequestParam String param) {
		
		return "";
	}
	
	@RequestMapping("/admin/blog/add")
	public String addBlog(Model model)
	{
		
		return "";
	}
	
	
	@RequestMapping("/admin/blog/search/{}")
	public String searchBlog(Model model)
	{
		
		
		return "";
	}
}
