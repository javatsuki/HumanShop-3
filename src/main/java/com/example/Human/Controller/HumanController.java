package com.example.Human.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//トップ画面へのマッピング
public class HumanController {
	
	@RequestMapping("/Index")
	public String Index(Model model) {
		model.addAttribute("name,");
		return "Index";
		
	}
}
