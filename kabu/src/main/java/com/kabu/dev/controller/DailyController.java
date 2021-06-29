package com.kabu.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.KLineOutDto;
import com.kabu.dev.dto.UserCollectionDto;
import com.kabu.dev.service.DailyService;
import com.kabu.dev.vo.ResultJson;

@RestController
@RequestMapping("/daily")
@CrossOrigin(origins = "*")
public class DailyController {
	@Autowired
	DailyService dailyService;
	@Autowired
	DailyEntityMapper dailyDao;
	
	
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
	@GetMapping("insert")
	public String createUserCollection(String stockId,String userId) {
		try{
			dailyService.createUserCollection(stockId,userId);
			return  "気に入りました";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return  "気に入らない";
			}
		
		
	}
	@GetMapping("collectionList")
	public List<DailyOutDto> listAll(String userId) {
		String LoginUserId = userId;
		List<DailyOutDto> list = dailyDao.selectmyfavourite(LoginUserId);
		
		return list;
	}
	@GetMapping("transHistoryList")
	public List<DailyOutDto> transHistoryList(String userId) {
		String LoginUserId = userId;
		List<DailyOutDto> list = dailyDao.transHistory(LoginUserId);
		
		return list;
	}
}
