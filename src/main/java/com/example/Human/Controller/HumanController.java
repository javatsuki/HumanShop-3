package com.example.Human.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Human.Repository.HumanRepository;
import com.example.Human.entity.Users;

@Controller
//トップ画面へのマッピング
public class HumanController {
	
	
	//トップページへのマッピングなので、DBの値を出すaddAttributeを書いてる
	@Autowired
	private HumanRepository repo;
	
	@RequestMapping("/Index")
	public String Index(Model model) {
		List<Users> users = repo.findAll();
		
		model.addAttribute("user_name", "名前");//カッコ内の左側が”キー”（箱の名前）、右側が"バリュー"（中身の名前）
		model.addAttribute("users", users);
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
