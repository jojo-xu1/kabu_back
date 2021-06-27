package com.kabu.dev.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.KLineDailyOutDto;
import com.kabu.dev.dto.UserCollectionDto;

public interface DailyEntityMapper {

	DailyOutDto selectByPrimaryKey(String stockId,String dayId);
    
    List<DailyOutDto> selectByDailyId(String dailyId);
    List<DailyOutDto> selectstockpool(); 
    List<DailyOutDto> selectstockpoolhigh();
    List<DailyOutDto> selectMA(String stockId,String dayId);
 
    List<KLineDailyOutDto> selectKline(@Param("stockId")String stockId,@Param("day")int day);
    
    List<DailyOutDto> selectByIdMini(String stockId);
    List<DailyOutDto> selectByIdMiniEx(@Param("stockId")String stockId,@Param("day")int day);
    List<DailyOutDto> selectMAListLow();

    List<DailyOutDto> selectFromStockTradeLow();
    List<DailyOutDto> selectFromStockTradeMid();
    List<DailyOutDto> selectFromStockTradeHigh();
    
	int insertUserCollection(String stockId,String userId);
    
    List<DailyOutDto> selectmyfavourite(String LoginUserId);
}