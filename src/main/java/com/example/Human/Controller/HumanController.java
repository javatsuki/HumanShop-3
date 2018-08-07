package com.example.Human.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Human.Mapper.ProductsMapper;
import com.example.Human.Mapper.UsersMapper;
import com.example.Human.entity.Products;
import com.example.Human.entity.Users;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
//トップ画面へのマッピング
public class HumanController {
	
	
	//トップページへのマッピングなので、DBの値を出すaddAttributeを書いてる
	@Autowired
	UsersMapper usersMapper;
	@Autowired
	ProductsMapper productsMapper;
	
	@GetMapping("/Index")
	public String Index(Model model) {
		//ユーザーマスタテーブル
		List<Users> list = usersMapper.selectAll();
		model.addAttribute("users", list);
		
		//商品マスタテーブル
		List<Products> productsList = productsMapper.selectAll();
		model.addAttribute("products", productsList);
		
		return "Index";
		

				
	}
	
	public void login(@RequestParam("login") String name) {
		
		char[] login;
		return;
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
