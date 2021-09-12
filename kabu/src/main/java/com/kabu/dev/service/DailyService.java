package com.kabu.dev.service;

import java.util.List;

import com.kabu.dev.dto.KLineOutDto;

public interface DailyService {

	KLineOutDto KlineService(String stockId,String day)throws Exception;
	
	KLineOutDto HisKlineService(String stockId, String hisDate)throws Exception;

	void createUserCollection(String stockId,String userId) throws Exception;
	
	void hisUserColltInsert(List<Integer> stockList,String userId) throws Exception;
	
}
