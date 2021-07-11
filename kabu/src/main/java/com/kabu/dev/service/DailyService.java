package com.kabu.dev.service;

import com.kabu.dev.dto.KLineOutDto;

public interface DailyService {

	KLineOutDto KlineService(String stockId,String day)throws Exception;

	void createUserCollection(String stockId,String userId) throws Exception;
	
	void hisUserColltInsert(String stockId,String userId) throws Exception;
	
}
