package com.kabu.dev.service;

import com.kabu.dev.dto.KLineOutDto;
import com.kabu.dev.dto.UserCollectionDto;

public interface DailyService {

	KLineOutDto KlineService(String stockId,String day)throws Exception;

	void createUserCollection(UserCollectionDto dto) throws Exception;
}
