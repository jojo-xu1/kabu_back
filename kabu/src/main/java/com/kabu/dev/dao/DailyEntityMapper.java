package com.kabu.dev.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.KLineDailyOutDto;

public interface DailyEntityMapper {

	DailyOutDto selectByPrimaryKey(String stockId,String dayId);
    
    List<DailyOutDto> selectByDailyId(String dailyId);
    List<DailyOutDto> selectstockpool(); 
    List<DailyOutDto> selectstockpoolhigh();
    List<DailyOutDto> selectMA(String stockId,String dayId);
 
    List<KLineDailyOutDto> selectKline(@Param("stockId")String stockId,@Param("day")int day);
    List<KLineDailyOutDto> selectHisKline(@Param("stockId")String stockId,@Param("strDate")String strDate,@Param("endDate")String endDate);
    
    
    List<DailyOutDto> selectByIdMini(String stockId);
    List<DailyOutDto> selectByIdMiniEx(@Param("stockId")String stockId,@Param("day")int day);
    List<DailyOutDto> selectHisListById(@Param("stockId")String stockId,@Param("day")int day,@Param("basedate")String basedate);
    List<DailyOutDto> selectMAListLow();

    List<DailyOutDto> selectFromStockTradeLow();
    List<DailyOutDto> selectFromStockTradeMid();
    List<DailyOutDto> selectFromStockTradeHigh();
    
	int insertUserCollection(String stockId,String userId);
    
    List<DailyOutDto> selectmyfavourite(String LoginUserId);
    List<DailyOutDto> transHistory(String LoginUserId);
    List<DailyOutDto> userTransHistory(String LoginUserId,String transDate,List<Integer> stockIdList);
    int deleteHisColltByUserId(String userId);
    int hisUserColltInsert(Integer stockId,String userId);
    List<Integer> hisUserColltList(String userId);
    List<Integer> hisAllCodeList();
    
}