package com.kabu.dev.dao;

import java.util.List;

import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.StockHistoryTradeDto;
import com.kabu.dev.dto.StockTradeDto;

public interface HistoryTradeEntityMapper {
	
	
	
	int deletetempstocktrade();
	List<DailyOutDto> selectstockpoollow(String dateNowStr);
	List<DailyOutDto> selectstockpool(String dateNowStr);
	List<DailyOutDto> selectstockpoolhigh(String dateNowStr);
	List<DailyOutDto> selectFromHistoryStockTrade();
	
	int updateEndByMoveLineDown(String endselldate,String stockid);
	int updateprice(String endselldate);
	int updateStockDate(String endselldate);
	int updateStockTradeUpdateFlag();
	int deletePriceZero();
	int inserttempstocktrade(StockTradeDto stockTradeDto);
	int updateEndBy10Day(String endselldate);
	int updateStockTradeDate(String endselldate);
	List<StockTradeDto> selectFromTempStockTrade();
	int insertstocktrade(StockHistoryTradeDto stockHistoryTradeDto);
	int selectFromRealstock(String dateNowStr);
}
