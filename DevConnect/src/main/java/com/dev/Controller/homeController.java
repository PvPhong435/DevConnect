package com.dev.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
	@GetMapping("/home/index")
	public String getHomePage(Model model) {
		
		model.addAttribute("list",list);
		return "views/index";
	}
}
