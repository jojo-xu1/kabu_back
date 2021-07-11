package com.kabu.dev.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
	private String dateNowStr;
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
					BigDecimal price =listToday.get(j).getEndPrice();
					//删除与既存重合的数据
					DailyTradeDao.deleteRecmByKey(stockId);	
					//插入新数据
					StockTradeDto stockTradeDto = new StockTradeDto();
					stockTradeDto.setStockId(stockId);
					stockTradeDto.setType(2);
					stockTradeDto.setStartbuydate(toDay.toString());//开始购入日
					stockTradeDto.setBuy_price(price);//
					stockTradeDto.setSell_price(null);//
					stockTradeDto.setEndselldate(toDay.toString());//停止出售日（买入日后九天）
					stockTradeDto.setUpdateflag(1);
					DailyTradeDao.insertRecmByKey(stockTradeDto);
				}
			}
			
		}
			
	}
	
	public void Dailybatch() throws Exception { 
		 dateNowStr = DailyTradeDao.selectNowStringDay();  
		 DailyTradeDao.deletetempstocktrade();
		 DailyByincome();//by income
		 DailyByMA();//by MA10 ,MA20
		
		 DailyByMAHigh();//by MA60 ,MA80
		 DailyTradeDao.updateprice();
		 WriteEndDay();//type =2
		 DailyTradeDao.updateStockDateByWin();
		 DailyTradeDao.updateStockDateByMidRisk();
		 DailyTradeDao.updateStockDateByLowRisk();
		 DailyTradeDao.updateStockTradeUpdateFlag();//updateflag=1
		 insertIntoStockTrade();

		System.out.println("come to Dailybatch");
	}
	
	private void DailyByincome() throws Exception {
		List<DailyOutDto> listTodayLow= dailyDao.selectMAListLow();
		for (int j=0;j<listTodayLow.size();j++) {
			//插入新数据
			String stockId = listTodayLow.get(j).getStock().getStockId();
			BigDecimal price =listTodayLow.get(j).getEndPrice();
			StockTradeDto stockTradeDto = new StockTradeDto();
			stockTradeDto.setStockId(stockId);
			stockTradeDto.setType(1);
			stockTradeDto.setStartbuydate(dateNowStr);//开始购入日
			stockTradeDto.setBuy_price(price);//
			stockTradeDto.setSell_price(null);//
			stockTradeDto.setEndselldate("");//停止出售日（买入日后九天）
			stockTradeDto.setUpdateflag(0);
			DailyTradeDao.inserttempstocktrade(stockTradeDto);
		}
	}	
	private void DailyByMA() throws Exception { 
			//获取今日份推荐股票
			List<DailyOutDto> list = dailyDao.selectstockpool();
			List<DailyOutDto> listToday = filter.setMA(list);
		for (int j=0;j<listToday.size();j++) {
			//插入新数据
			String stockId = listToday.get(j).getStock().getStockId();
			BigDecimal price =listToday.get(j).getEndPrice();
			StockTradeDto stockTradeDto = new StockTradeDto();
			stockTradeDto.setStockId(stockId);
			stockTradeDto.setType(2);
			stockTradeDto.setStartbuydate(dateNowStr);//开始购入日
			stockTradeDto.setBuy_price(price);//
			stockTradeDto.setSell_price(null);//
			stockTradeDto.setEndselldate("");//停止出售日（买入日后九天）
			stockTradeDto.setUpdateflag(0);
			DailyTradeDao.inserttempstocktrade(stockTradeDto);
		}
	}
	
	private void DailyByMAHigh() throws Exception { 
		//获取今日份推荐股票
		List<DailyOutDto> list = dailyDao.selectstockpoolhigh();
		List<DailyOutDto> listToday = filter.setMAHigh(list);
	for (int j=0;j<listToday.size();j++) {
		//插入新数据
		String stockId = listToday.get(j).getStock().getStockId();
		BigDecimal price =listToday.get(j).getEndPrice();
		StockTradeDto stockTradeDto = new StockTradeDto();
		stockTradeDto.setStockId(stockId);
		stockTradeDto.setType(3);
		stockTradeDto.setStartbuydate(dateNowStr);//开始购入日
		stockTradeDto.setBuy_price(price);//
		stockTradeDto.setSell_price(null);//
		stockTradeDto.setEndselldate("");//停止出售日（买入日后九天）
		stockTradeDto.setUpdateflag(0);
		DailyTradeDao.inserttempstocktrade(stockTradeDto);
	}
}
	
	private void WriteEndDay() throws Exception {
		DailyTradeDao.updateStockTradeDate(dateNowStr);	
	}	
	
	private void insertIntoStockTrade() throws Exception {
			//获取今日份推荐股票
			List<StockTradeDto> listToday = DailyTradeDao.selectFromTempStockTrade();
		for (int j=0;j<listToday.size();j++) {
			//插入新数据
			StockTradeDto stockTradeDto = new StockTradeDto();
			stockTradeDto.setStockId(listToday.get(j).getStockId());
			stockTradeDto.setType(listToday.get(j).getType());
			stockTradeDto.setStartbuydate(listToday.get(j).getStartbuydate());//开始购入日
			stockTradeDto.setBuy_price(listToday.get(j).getBuy_price());//
			stockTradeDto.setSell_price(listToday.get(j).getSell_price());//
			stockTradeDto.setEndselldate(listToday.get(j).getEndselldate());//停止出售日（买入日后九天）
			stockTradeDto.setUpdateflag(0);
			DailyTradeDao.insertstocktrade(stockTradeDto);
		}
	}	
}
