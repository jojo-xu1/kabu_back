package com.kabu.dev.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kabu.dev.dto.StockOutDto;
import com.kabu.dev.service.StockMasterService;
import com.kabu.dev.vo.ResultJson;

@RestController
@RequestMapping("/stock")
@CrossOrigin(origins = "*")
public class StockController {
	@Autowired
	StockMasterService stockMasterService;
	
	@GetMapping("/dailyList")
	public ResultJson dailyList(String num,String size,String dailyId,String flag) {
		try {
			Map<String,Object> map=stockMasterService.queryDailyList(num, size, dailyId, flag);
			ResultJson result = ResultJson.success(map);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
		}
	}
	
	@GetMapping("/stockInfo")
	public ResultJson stockInfo(String stockId) {
		try {
			StockOutDto out=stockMasterService.queryStockInfo(stockId);
			ResultJson result = ResultJson.success(out);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResultJson result =ResultJson.fail(e);
			return result;
		}
	}
}
