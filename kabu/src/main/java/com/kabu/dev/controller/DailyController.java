package com.kabu.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kabu.dev.dto.KLineOutDto;
import com.kabu.dev.service.DailyService;
import com.kabu.dev.vo.ResultJson;

@RestController
@RequestMapping("/daily")
@CrossOrigin(origins = "*")
public class DailyController {
	@Autowired
	DailyService dailyService;
	
	@GetMapping("kline")
	public ResultJson getkline(String stockId,String day) {
		try {
			KLineOutDto k=dailyService.KlineService(stockId, day);
			ResultJson ret=ResultJson.success(k);
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultJson ret=ResultJson.fail(e);
			return ret;
		}
		
		
	}
}
