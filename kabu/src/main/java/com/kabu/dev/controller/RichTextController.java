package com.kabu.dev.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.kabu.dev.dto.RichTextInDto;
import com.kabu.dev.service.RichTextService;
import com.kabu.dev.vo.ResultJson;

@Controller
@RequestMapping("/rich-text")
@CrossOrigin(origins = "*")
public class RichTextController {
	
	@Autowired
	RichTextService richTextService;
	
	@PostMapping("/create")
	public String createRichText(RichTextInDto inDto) {
		System.out.println("登録.................");
		try{
		richTextService.createRichText(inDto);
		return "index.html";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  "index.html";
		}
	}
	
	@GetMapping("/list")
	@ResponseBody
	public ResultJson listRichText(String columnId,String num,String size) {		
		try {
			Map<String,Object> map = richTextService.queryListRichText(columnId, num, size);
			ResultJson result = ResultJson.success(map);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
		}
		
	}
	
	@GetMapping("/get")
	@ResponseBody
	public ResultJson getText(String id) {
		try {
			Map<String,Object> map = richTextService.queryRichText(id);
			ResultJson result = ResultJson.success(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
			
		}
		
	}
}
