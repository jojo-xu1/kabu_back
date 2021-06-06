package com.kabu.dev.dao;

import java.util.Date;
import java.util.List;

import com.kabu.dev.dto.StockTradeDto;

public interface StockTradeEntityMapper {
	
	List<StockTradeDto> selectYesdata();
	int updateDailyRecmBykey(String stockId);
	int updateStockTradeDate(String stockId,String endselldate);	
	int deletetempstocktrade();
	int deleteRecmByKey(String stockId);
	int insertRecmByKey(StockTradeDto stockTradeDto);
	int inserttempstocktrade(StockTradeDto stockTradeDto);
}
