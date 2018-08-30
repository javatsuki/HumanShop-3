package com.example.Human.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Human.Mapper.ProductsDetailMapper;
import com.example.Human.Mapper.ProductsMapper;
import com.example.Human.Mapper.UsersMapper;
import com.example.Human.entity.Products;
import com.example.Human.entity.ProductsDetail;
import com.example.Human.entity.Users;
import com.example.Human.service.LoginUser;
import com.example.Human.service.UserInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
//トップ画面へのマッピング
public class HumanController {
	

	//トップページへのマッピングなので、DBの値を出すaddAttributeを書いてる
	@Autowired
	UsersMapper usersMapper;
	@Autowired
	ProductsMapper productsMapper;
	ProductsDetailMapper productsDetailMapper;
	
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
	
	//マイページのページへのマッピング
	@GetMapping("/User")
	public String User(Model model) {
		List<Users> list = usersMapper.selectAll();
		model.addAttribute("users", list);
		return "User";
	}
	
	@GetMapping("/Products")
	public String ShowProducts(Model model) {
		//商品マスタテーブル
		List<Products> productsList = productsMapper.selectAll();//productsクラスの型のリスト　　に、セレクトした画像、名前、詳細を　入れ込んでる
		model.addAttribute("products", productsList);//モデルのデータをヴューに表示させる
		return "Products";		
	}
	
	//モーダルウィンドウでの商品詳細8/30
	@GetMapping("/ShowProductsDetail")
	public String ShowProductsDetail(Model model ) {
		List<ProductsDetail> ProductsDetailslList = productsDetailMapper.products_detail();
		model.addAttribute("productsDetail");
		return "Products";		
	}
	
	
	@PostMapping(value = "/Login")
    public String showLogin(LoginUser form, Model model) {
		
        return "Login";
        
    }
	
	@PostMapping(value = "/LoginPage")
    public String mainLogin(LoginUser form, Model model) {
		
		usersMapper.selectLoginUser(form);
        return "redirect:/Products";
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
		
		return "redirect:/Products";
	}
	
	
	
	//新規登録画面のページへのマッピングをを
	@GetMapping("/NewAcount")
	public String NewAcount() {
		return "NewAcount";
	}
	 


	//カートのページへのマッピング
	@GetMapping("/Cart")
	public String Cart() {
		return "Cart";
	}
	
	//上西の商品詳細へのマッピング
	@GetMapping("/uenishi")
	public String Uenishi() {
		return "uenishi";
	}

	@GetMapping("/detail")
	public String Detail(Model model) {
		List<Products> productsList = productsMapper.selectAll();
		model.addAttribute("products", productsList);
		return "detail";
	}
	
	@GetMapping("/sample2")
	public String sample2() {
		return "sample2";
	}
	
	@GetMapping("/sample")
	public String sample() {
		return "sample";
	}
	
	//[商品詳細]ボタンを押したときのajaxの動き
	@RequestMapping(value = "getProductsId", method = RequestMethod.GET)
	@ResponseBody
	
	public String[] getProductsId(String zaki) throws JsonParseException {
		String[] datas = {zaki, "え…、", "えいじゃっくすが…", "動いてるやないか！！", "もう完成したも同然やな"};
	    return datas;
	}
	/*
	public String getTestData(String zaki) throws JsonParseException {
		String datas = zaki;
	    return datas;
	}*/
	
	//ajax投げるサンプル　http://ponkotsusechan.hatenadiary.jp/entry/2016/02/01/173000_1
    @ResponseBody
	@RequestMapping(value = "/searchFileInfoJson", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	public String searchFileInfoJson(@RequestBody String json) throws IOException, ServletException {
        //処理をあれこれ
    	return "でけた！！";
    }
}
