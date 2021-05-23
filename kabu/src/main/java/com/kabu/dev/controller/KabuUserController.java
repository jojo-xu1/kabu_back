package com.kabu.dev.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kabu.dev.service.KabuUserService;
import com.kabu.dev.service.KabuVideoService;
import com.kabu.dev.vo.ResultJson;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kabu-user")
public class KabuUserController {
	
	@Autowired
	KabuUserService kabuUserService;
	
	
	@GetMapping("/login")
	public ResultJson getPassword(HttpServletRequest req,String user_id,String password) throws Exception {
		System.out.println("print user_id:"+user_id);
		ResultJson result = null;
		HttpSession session=req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		session=req.getSession();
		if(password.equals(kabuUserService.queryKabuUser(user_id))) {
			Map<String,Object> map = kabuUserService.queryUserDetails(user_id);
			session.setAttribute("userInfo", map);
			result = ResultJson.success(map);
		}else {
			result = ResultJson.passwordWrong();
		}
		System.out.println("getPassword方法里打印密码:"+password);
		
		return result;
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		session.invalidate();
		System.out.println("测试调用了logout");
	}

	
	@RequestMapping("/createUser")
	 public void createUser(String userId,String password){
	  System.out.println(userId);
	  kabuUserService.createUser(userId,password);
	 
	 }
	
	@RequestMapping("/updateUser")
	 public void updateUser(@RequestParam("userId") String user_id,@RequestParam("password")String password){

	  kabuUserService.updateUser(user_id,password);
	 
	 }
	
}
