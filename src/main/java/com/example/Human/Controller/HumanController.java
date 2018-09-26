package com.example.Human.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xmlunit.builder.Input;

import com.example.Human.Mapper.ProductsMapper;
import com.example.Human.Mapper.UsersMapper;
import com.example.Human.entity.Products;
import com.example.Human.entity.Users;
import com.example.Human.service.LoginUser;
import com.example.Human.service.UserInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@SessionAttributes(names = "reqForm")//セッション保持のアノテーションやで//
//@SessionAttributes(value = "inputForm")


public class HumanController {

	

	@Autowired
	UsersMapper usersMapper;
	@Autowired
	ProductsMapper productsMapper;
	
	//トップページへのマッピングなので、DBの値を出すaddAttributeを書いてる
	@GetMapping("/Index")
	public String index(Model model) {
		//ユーザーマスタテーブル
		List<Users> list = usersMapper.selectAll();
		model.addAttribute("users", list);
		
		//商品マスタテーブル
		List<Products> productsList = productsMapper.selectAll();
		model.addAttribute("products", productsList);
		
		return "Index";		
	} 
	
	//新規登録画面のページへのマッピング
	@GetMapping("/NewAcount")
	public String newAcount() {
		return "NewAcount";
	}

	//新規登録したら、DBに情報を挿入して、そのままログインページへリダイレクト
	@PostMapping(value = "/NewLogin")
    public String getLogin(UserInfo form, Model model) {
		
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
	@PostMapping(value = "/LoginPage")
    public @ResponseBody String mainLogin(LoginUser form) {
		List<Users> loginList = usersMapper.selectLoginUser();//select文で引っ張った内容を、Users型のloginListに代入
		 for(int i = 0; i < loginList.size(); i++){
	            Users u = loginList.get(i);//DB情報が入ったリストの、〇個めを u　に代入
	            if(form.getUser_id().equals(u.getUser_id()) &&//フォームのidと、リストの〇個目のidとを比較
	            		form.getPassword().equals(u.getPassword())) {

					//return "redirect:/CreateSession";
	            	//return "/CreateSession";
	               	Model model = null;
	               /*	String str1 = "redirect:";
	               	String str2 = request(model, form);//←最終的にProductsのファイルをリターンしている
	            	return str1 + str2;//←これがProductsファイルへのリダイレクト*/
	               	return "redirect:" + request(model, form);
	               	}
	        }
		 return "/MissLogin";
	}
	
	
	//セッション保持こっち側を参照→https://qiita.com/shibafu/items/f46f0fd529554b8cc1b2
	//@PostMapping(value = "CreateSession")//"hoge/result"から変えた　　←マッピングいらないよby勝
	public String request(Model model,
			@ModelAttribute("RequestForm")LoginUser arg_rq) {//リクエストで送信されてきた。この情報はセッションに保存されない
		//セッション格納
		setRequestForm(arg_rq);//あれ？コメントアウトしてもセッション格納されてるんだが…
		//return "hoge/result";
		//return "redirect:/SetSession";//これは何にしても一緒？
		String req = request2(model, arg_rq);
		return req;
	}
	
	//@PostMapping(value = "SetSession")//Post→Getに変えた　　←このマッピングもいらないよby勝
	public String request2(Model model,
			@ModelAttribute("reqFom")LoginUser session_rq) {//セッションから引き出しているオブジェクト
		//好きなデータを使いましょう
		session_rq.getUser_id();
		return "/Products";
	}
	//↓↓まったく仕事してないで　by勝
	//肝となる セッションのオブジェクト代入格納メソッド
	@ModelAttribute("reqForm")//このメソッドをコメントアウトしたら「こんにちはnull」になる
	public LoginUser setRequestForm(LoginUser requesetForm) {
		return requesetForm;
	}
	
	/*@GetMapping(value = "/complete")
	public String complete(
			@Validated(value = {LoginUser.Input1.class, LoginUser.Input2.class, LoginUser.Input3.class}) LoginUser form,
			BindingResult result,
			SessionStatus sessionStatus,
			RedirectAttributes redirectAttributes) {
				if(result.hasErrors()) {
					return "Users";
				}
			//いらなくなったので破棄
			sessionStatus.setComplete();
			redirectAttributes.addFlashAttribute("LoginUser", form);
			return "redirect:/Index";
	}
	*/
	
	//セッション保持こちらを参考→https://blog.okazuki.jp/entry/2015/07/05/214538
	/*@ModelAttribute("inputForm")
	LoginUser inputForm() {
		System.out.println("inputForm渡せてるやん！！セッションでけとるやん！！！");
		return new LoginUser();
	}
	//これが呼ばれる前にinputForm()メソッドが呼ばれる
	@GetMapping(value = "/input1")
	public String input1(LoginUser form) {
		return "User";
	}
	//必要なくなったらSessionStatesのCompleteで破棄できます
	@GetMapping(value = "/complete")
	public String complete(
			@Validated(value = {LoginUser.Input1.class, LoginUser.Input2.class, LoginUser.Input3.class}) LoginUser form,
			BindingResult result,
			SessionStatus sessionStatus,
			RedirectAttributes redirectAttributes) {
				if(result.hasErrors()) {
					return "Users";
				}
			//いらなくなったので破棄
			sessionStatus.setComplete();
			redirectAttributes.addFlashAttribute("LoginUser", form);
			return "redirect:/Index";
	}*/
     
	
	
	
	
	//ログイン失敗ページへのマッピング
	@GetMapping("/MissLogin")
	public String missLogin() {
		return "MissLogin";
	}

	//商品一覧ページ
	@PostMapping("/Products")
	public String showProducts(Model model) {
		List<Products> productsList = productsMapper.selectAll();//productsクラスの型のリスト　　に、セレクトした画像、名前、詳細を　入れ込んでる
		model.addAttribute("products", productsList);//モデルのデータをヴューに表示させる
		return "Products";		
	}

	//マイページのページへのマッピング
	@GetMapping("/User")
	public String user(Model model) {
		List<Users> list = usersMapper.selectAll();
		model.addAttribute("users", list);
		return "User";
	}
	/*@GetMapping("/detail")
	public String Detail(Model model) {
		List<Products> productslList = productsMapper.products_detail();//変更前：productsMapper.『products_detail』の部分を変えればいいだけ！！
		model.addAttribute("productsDetail", productslList);//変更前："products"
		return "detail";
	}*/
	
	/*@PostMapping(value = "/Login")
    public String showLogin(LoginUser form, Model model) {	
        return "Login";  
    }*/
	
	
	
	//ログイン処理…じゃないぽい。。たぶん要らん
	/*@PostMapping("/Products")
	public String login(LoginUser form, Model model) {
		Users users = usersMapper.selectLoginUser(form);
		//List<Users>
		if(users.getUser_name()!=null && users.getPassword()!=null) {
			return "redirect:/Products";

		}else {
			return "/MissLogin";
		}
	}*/


	//カートのページへのマッピング
	@GetMapping("/Cart")
	public String cart() {
		return "Cart";
	}	

	//[商品詳細ボタンを押すと、それぞれにIDが付与されて、そのIDでselectしたレコードをモーダルで表示させる]
	@RequestMapping(value = "getProductsList", method = RequestMethod.GET)
	@ResponseBody
	public List<Products> getProductsList(String zaki) {  	
		String datas = zaki;
		List<Products> productslList = productsMapper.products_detail(datas);//呼び出す側は抽象的な値にせねば
		//mav.addObject("products", productslList);
		return productslList;//リターンしたいのは、IDを代入してselectしてきたSQLのレコード！！	
	}
	
	
	//カートに入れるボタンでモーダル開く処理
	@RequestMapping(value = "getIntoCart", method = RequestMethod.GET)
	@ResponseBody
	public List<Products> getIntoCart(String inCartHuman) {  	
		String adatas = inCartHuman;
		List<Products> productslList = productsMapper.products_detail(adatas);//呼び出す側は抽象的な値にせねば
		return productslList;
	}
	
	
	//ajax投げるサンプル　http://ponkotsusechan.hatenadiary.jp/entry/2016/02/01/173000_1
    @ResponseBody
	@RequestMapping(value = "/searchFileInfoJson", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
	public String searchFileInfoJson(@RequestBody String json) throws IOException, ServletException {
        //処理をあれこれ
    	return "でけた！！";
    }
}
