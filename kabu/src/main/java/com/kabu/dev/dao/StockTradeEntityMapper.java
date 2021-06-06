package com.kabu.dev.dao;

import java.util.Date;
import java.util.List;

import com.kabu.dev.dto.StockTradeDto;

public interface StockTradeEntityMapper {
	
	List<StockTradeDto> selectYesdata();
	int updateDailyRecmBykey(String stockId);
	int updateStockTradeDate(String endselldate);
	int updateStockTradeUpdateFlag();
	List<StockTradeDto> selectFromTempStockTrade();
	int deletetempstocktrade();
	int deleteRecmByKey(String stockId);
	int insertRecmByKey(StockTradeDto stockTradeDto);
	int inserttempstocktrade(StockTradeDto stockTradeDto);
	int insertstocktrade(StockTradeDto stockTradeDto);
	String selectNowStringDay();
}
