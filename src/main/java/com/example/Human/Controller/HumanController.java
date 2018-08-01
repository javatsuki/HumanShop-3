package com.example.Human.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//トップ画面へのマッピング
public class HumanController {
	
	//トップページへのマッピングなので、DBの値を出すaddAttributeを書いてる
	@RequestMapping("/Index")
	public String Index(Model model) {
		model.addAttribute("user_name", "名前");//カッコ内の左側が”キー”（箱の名前）、右側が"バリュー"（中身の名前）
		return "Index";	
		
	}
	
	//ログインページへのマッピング
	@GetMapping("/Login")
	public String Login(Model model) {
		return "Login";
	}
	
	//カートのページへのマッピング
	@GetMapping("/Cart")
	public String Cart() {
		return "Cart";
	}
	
	//マイページへのマッピング
	@GetMapping("/User")
	public String User() {
		return "User";
	}
}
