package com.kabu.dev.service;

import java.util.Map;

import com.kabu.dev.dto.StockOutDto;

public interface StockMasterService {
	Map<String, Object> queryDailyList(String num,String size,String dailyId,String flag)throws Exception;
	StockOutDto queryStockInfo(String stockId)throws Exception;
}
