package com.kabu.dev.dao;

import java.util.Date;
import java.util.List;

import com.kabu.dev.dto.StockTradeDto;

public interface StockTradeEntityMapper {
	//int updateDailyRecm(String user,Date toDay);
	List<StockTradeDto> selectYesdata();
	int updateDailyRecmBykey(String stockId);
	int updateDailyRecm(Date toDay);
	int deleteRecmByKey(String stockId);
	int insertRecmByKey(StockTradeDto stockTradeDto);
}
