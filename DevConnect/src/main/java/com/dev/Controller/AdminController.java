package com.dev.Controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dev.Dao.UserDao;
import com.dev.Model.User;

@Controller
public class AdminController {
	
	@Autowired 
	UserDao dao;
	
	@GetMapping("/admin/user")
	public String adminManager(Model model)
	{
		model.addAttribute("users", dao.findAll());
		
		return "Admin/UserAdmin";
	}
	
	@RequestMapping("/admin/user/edit/{id}")
	public String editManagetment(Model model,@PathVariable("id") String username)
	{
		model.addAttribute("user", dao.findByUsername(username));
		return "/Admin/UserForm";
	}
	
	@RequestMapping("/admin/users/save")
	public String saveUser(Model model,@ModelAttribute("User") User user/*,@RequestParam("img-user")MultipartFile imgFile*/)
	{
		try {
			
//			 //MultipartFile imgFile = img-user;
//	            if (imgFile != null && !imgFile.isEmpty()) {
//	                String fileName = imgFile.getOriginalFilename();
//	                Path path = Path.of("path/to/save/directory", fileName);
//	                Files.copy(imgFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//	                user.setImg(fileName); // Lưu tên file vào cơ sở dữ liệu
//	            }
			dao.save(user);
			System.out.println("Thêm dữ liệu thành công");
			return "redirect:/admin/user";
		} catch (Exception e) {
			System.out.println("Thêm dữ liệu thất bại");
			return "/Admin/UserForm";
		}		
	}
	
	@RequestMapping("/admin/user/")
	
}
