package com.kabu.dev.service;

import com.kabu.dev.dto.KLineOutDto;

public interface DailyService {

	KLineOutDto KlineService(String stockId,String day)throws Exception;
}
