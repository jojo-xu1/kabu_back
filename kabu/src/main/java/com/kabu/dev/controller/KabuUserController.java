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
	

	
	@GetMapping("/getStatus")
	public ResultJson getStatus(HttpServletRequest request) throws Exception {
		
			//1.获取session
			HttpSession session = request.getSession(false);
			String status ="";
			
			if(session != null) {
				
				if(session.getAttribute("userInfo") != null) {
					status = "1";
				}else {
					status = "0";
				}
			}else {
				
				status = "0";
			}
			
			System.out.println(status);
			ResultJson result = ResultJson.success(status);
			
			return result;
	
	}
	
	@PostMapping("/createUser")
	public void createUser(String user_id,String password){

		kabuUserService.createUser(user_id,password);
	
	}
	
	@PostMapping("/updateUser")
	public void updateUser(String user_id,String password){

		kabuUserService.updateUser(user_id,password);
	
	}
	
}
