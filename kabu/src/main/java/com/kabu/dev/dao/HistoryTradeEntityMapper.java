package com.kabu.dev.dao;

import java.util.List;

import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.StockHistoryTradeDto;
import com.kabu.dev.dto.StockTradeDto;

public interface HistoryTradeEntityMapper {
	
	
	
	int deletetempstocktrade();
	List<DailyOutDto> selectstockpool(String dateNowStr);
	int updateprice(String endselldate);
	int updateStockDate();
	int updateStockTradeUpdateFlag();
	int inserttempstocktrade(StockTradeDto stockTradeDto);
	int updateStockTradeDate(String endselldate);
	List<StockTradeDto> selectFromTempStockTrade();
	int insertstocktrade(StockHistoryTradeDto stockHistoryTradeDto);
}
