package com.kabu.dev.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kabu.dev.dto.KabuVideoInDto;
import com.kabu.dev.service.KabuVideoService;
import com.kabu.dev.vo.ResultJson;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kabu-video")
public class KabuVideoController {
	
	@Autowired
	KabuVideoService kabuVideoService;
	
	@PostMapping("/create")
	public ResultJson createKabuVideo(KabuVideoInDto dto) {
		try {
			kabuVideoService.createKabuVideo(dto);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
			
		}
	}
	
	@GetMapping("/Searchlist")
	public ResultJson listKabuVideo(String keyWord) {
		try {
			Map<String,Object> map = kabuVideoService.queryListKabuVideo(keyWord);
			ResultJson result = ResultJson.success(map);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
		}
		
	}
	
	@GetMapping("/listAll")
	public ResultJson listAllKabuVideo(String num,String pageSize) {

		try {
			Map<String,Object> map = kabuVideoService.listAllKabuVideo(num,pageSize);
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
	public ResultJson getText(String id) {
		try {
			Map<String,Object> map = kabuVideoService.queryKabuVideo(id);
			ResultJson result = ResultJson.success(map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
			
		}
		
	}
}
