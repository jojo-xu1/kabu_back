package com.kabu.dev.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dao.StockTradeEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.StockTradeDto;

@Component
public class DailyRecmBatch {
	@Autowired
	StockTradeEntityMapper DailyTradeDao;
	@Autowired
	DailyEntityMapper dailyDao;
	@Autowired
	DailyFilter filter;
	/**
	 * 每日股票推荐batch
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	
	/* @Scheduled(cron="0 15 11 ? * * ") */
	public void DailyRecmUpdate(String user) throws Exception {
		//获取系统时间
		Date toDay=new Date();
		
		//获取当前推荐中的股票
		List<StockTradeDto> listYesterday = DailyTradeDao.selectYesdata();
		//获取今日份推荐股票
		List<DailyOutDto> list = dailyDao.selectByDailyId(toDay.toString());
		List<DailyOutDto> listToday = filter.setMA(list);
		
		//未在今日推荐中的股票，update='0'
		for (int i=0;i<listYesterday.size();i++) {
			for (int j=0;j<listToday.size();j++) {
				if(listYesterday.get(i).getStockId() == listToday.get(j).getStock().getStockId()) {
					continue;
				}else {
					//更新update值
					DailyTradeDao.updateDailyRecmBykey(listYesterday.get(i).getStockId());
				}
			}
			
		}
		//将今日份推荐插入数据库
		for (int i=0;i<listToday.size();i++) {
			for (int j=0;j<listYesterday.size();j++) {
				if(listYesterday.get(j).getStockId() == listToday.get(i).getStock().getStockId()) {
					continue;//跳过当前数据库中正在推荐的股票
				}else {
					String stockId = listToday.get(i).getStock().getStockId();
					//删除与既存重合的数据
					DailyTradeDao.deleteRecmByKey(stockId);	
					//插入新数据
					StockTradeDto stockTradeDto = null;
					stockTradeDto.setStockId(stockId);
					stockTradeDto.setStartbuydate(toDay.toString());//开始购入日
					stockTradeDto.setEndbuydate(toDay.toString());//停止购入日（买入日后三天）
					stockTradeDto.setStartselldate(toDay.toString());//开始出售日（买入日后六天）
					stockTradeDto.setEndselldate(toDay.toString());//停止出售日（买入日后九天）
					stockTradeDto.setUpdateflag("1");
					DailyTradeDao.insertRecmByKey(stockTradeDto);
				}
			}
			
		}
			
	}
	
}
