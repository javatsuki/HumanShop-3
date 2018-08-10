package com.example.Human.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Human.Mapper.ProductsMapper;
import com.example.Human.Mapper.UsersMapper;
import com.example.Human.entity.Products;
import com.example.Human.entity.Users;
import com.example.Human.service.LoginUser;
import com.example.Human.service.UserInfo;

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
	
//	@GetMapping("/Products")
//	public String ShowProducts(Model model) {
//		//商品マスタテーブル
//		List<Products> productsList = productsMapper.selectAll();
//		model.addAttribute("products", productsList);
//		
//		return "Products";		
//	}
//	

	@PostMapping(value = "/Login")
    public String post(UserInfo form, Model model) {
        return "Login";
        
        
        
    }
	
	@PostMapping(value = "/NewLogin")
    public String getLogin(UserInfo form, Model model) {
		//System.out.println(form.getUserId());
		//System.out.println(form.getPassword());
		String userId = form.getUserId();
		String userName = form.getUserName();
		String password = form.getPassword();
		String address1 = form.getAddress1();
		String address2 = form.getAddress2();
		String address3 = form.getAddress3();
		
		Users users = new Users();
		users.setUser_id(userId);
		users.setUser_name(userName);
		users.setPassword(password);
		users.setAddress1(address1);
		users.setAddress2(address2);
		users.setAddress3(address3);
		
		usersMapper.insertUserInfo(users);
		
        model.addAttribute("userId", userId);
        model.addAttribute("userName", userName);
        model.addAttribute("password", password);
        model.addAttribute("address1", address1);
        model.addAttribute("address2", address2);
        model.addAttribute("address3", address3);
        return "redirect:/Login";
        
	}
	

	//ログイン処理
	@PostMapping("/Products")
	public String login(LoginUser form, Model model) {
		
		Users users = usersMapper.selectLoginUser(form);
		
		return "Products";
	}
	
	
	
	//新規登録画面のページへのマッピング
	@GetMapping("/NewAcount")
	public String NewAcount() {
		return "NewAcount";
	}
	 

	
	//カートのページへのマッピング
	@GetMapping("/Cart")
	public String Cart() {
		return "Cart";
	}
	
	
	//マイページのページへのマッピング
	@GetMapping("/User")
	public String User() {
		return "User";
	}
	
	//商品一覧のページへのマッピング
		@GetMapping("/Products")
		public String Products() {
			return "Products";
		}
	

}
