package com.kabu.dev.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dao.StockEntityMapper;
import com.kabu.dev.dto.KLineDailyOutDto;
import com.kabu.dev.dto.KLineOutDto;
import com.kabu.dev.dto.StockOutDto;
import com.kabu.dev.service.DailyService;
@Service
public class DailyServiceImpl implements DailyService{
	@Autowired
	DailyEntityMapper dailyDao;
	@Autowired
	StockEntityMapper stockDao;
	
	@Override
	public KLineOutDto KlineService(String stockId, String day) throws Exception {
		
		
		if(StringUtil.isEmpty(day)) {
			day="15";
		}
		
		KLineOutDto k=new KLineOutDto();
		StockOutDto stock = stockDao.selectByPrimaryKey(stockId);
		k.setStockId(stock.getStockId());
		k.setStockName(stock.getStockName());
		
		List<KLineDailyOutDto> list=dailyDao.selectKline(stockId, Integer.parseInt(day));
		k.setkLineDailyOutDto(list);
		return k;
	}
	
	@Override
	public KLineOutDto HisKlineService(String stockId,  String hisDate) throws Exception {
		
		KLineOutDto k=new KLineOutDto();
		StockOutDto stock = stockDao.selectByPrimaryKey(stockId);
		k.setStockId(stock.getStockId());
		k.setStockName(stock.getStockName());
		
		
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
		
		Date date = sdf.parse(hisDate); 
		
		Calendar Date1 = Calendar.getInstance();
		Calendar Date2 = Calendar.getInstance();
		//下个月末后一天
		Date1.setTime(date);
		Date1.add(Calendar.MONTH, 1);
		Date1.set(Calendar.DAY_OF_MONTH, 1);
		//上个月初前一天
		Date2.setTime(date);
		Date2.add(Calendar.MONTH, -3);
		Date2.set(Calendar.DAY_OF_MONTH,Date2.getActualMaximum(Calendar.DAY_OF_MONTH) );
		//日期转字符串
		String endDate = sdf.format(Date1.getTime());
		String strDate = sdf.format(Date2.getTime());
		
		List<KLineDailyOutDto> list=dailyDao.selectHisKline(stockId,strDate,endDate );
		k.setkLineDailyOutDto(list);
		return k;
	}
	
	@Override
	public void createUserCollection(String stockId,String userId) throws Exception {
		dailyDao.insertUserCollection(stockId,userId);
		
	}
	
	@Override
	public void hisUserColltInsert(List<Integer> stockList,String userId) throws Exception {
		if (stockList.size()>0) {
			//清空表中历史检索记录
			dailyDao.deleteHisColltByUserId(userId);
			for(int i=0;i<stockList.size();i++) {
				dailyDao.hisUserColltInsert(stockList.get(i),userId);
			}
			
		
		
		}
		
	}

}
