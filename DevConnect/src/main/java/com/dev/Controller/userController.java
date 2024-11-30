package com.dev.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.Dao.ArticleDAO;
import com.dev.Dao.SavedArticleDAO;
import com.dev.Dao.UserDao;
import com.dev.Model.Article;
import com.dev.Model.SavedArticle;
import com.dev.Model.SavedArticleKey;
import com.dev.Model.User;
import com.dev.services.UserPrincipal;

@Controller
public class userController {

	SavedArticleDAO savedArticleDAO;
	UserDao userDAO;
	ArticleDAO articleDAO;
	
	
	
	
	public userController(SavedArticleDAO savedArticleDAO, UserDao userDAO, ArticleDAO articleDAO) {
		super();
		this.savedArticleDAO = savedArticleDAO;
		this.userDAO = userDAO;
		this.articleDAO = articleDAO;
	}




	@GetMapping("/user/bookmarks")
	public String userBookmarks(Model model,@AuthenticationPrincipal UserPrincipal userPrincipal) {
		String username=userPrincipal.getUsername();
		User user=userDAO.findById(username).orElse(null);
		if(user==null) {
			return "error/ServerError";
		}
		List<SavedArticle> bookmarks=savedArticleDAO.findByUser(user);
		model.addAttribute("bookmarks",bookmarks);
		return "user/usersavedarticle";
	}
	
	@PostMapping("/user/bookmark/{id}")
	@ResponseBody
	public Map<String,String> addBookmark(@PathVariable String id,@AuthenticationPrincipal UserPrincipal userPrincipal) {
		String username=userPrincipal.getUsername();
		User user=userDAO.findById(username).orElse(null);
		System.out.println(id);
		Article article=articleDAO.findById(id).orElse(null);
		//Check for duplicate
		SavedArticle savedArticle=savedArticleDAO.findByUserAndArticle(user, article).orElse(null);
		if(savedArticle==null) {
			SavedArticleKey key=new SavedArticleKey(user.getUsername(),article.getId());
			savedArticle=new SavedArticle(key, user, article, Date.valueOf(LocalDate.now()), 0);
			try {
				savedArticleDAO.save(savedArticle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				savedArticleDAO.delete(savedArticle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map<String,String> response=new HashMap<>();
		response.put("message", "Article "+id+" bookmarked successfully by "+user.getFullname());
		return response;
	}
	
	@DeleteMapping("/user/bookmark/{id}")
	@ResponseBody
	public ResponseEntity<Void> deleteBookmark(@PathVariable String article_id,@AuthenticationPrincipal UserPrincipal userPrincipal){
		String username=userPrincipal.getUsername();
		SavedArticleKey savedArticleKey=new SavedArticleKey(username, article_id);
		savedArticleDAO.deleteById(savedArticleKey);
		return ResponseEntity.ok().build();
	}
}
