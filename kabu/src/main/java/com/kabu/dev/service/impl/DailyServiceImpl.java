package com.kabu.dev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dao.StockEntityMapper;
import com.kabu.dev.dto.KLineDailyOutDto;
import com.kabu.dev.dto.KLineOutDto;
import com.kabu.dev.dto.StockOutDto;
import com.kabu.dev.dto.UserCollectionDto;
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
	public void createUserCollection(UserCollectionDto dto) throws Exception {
		dailyDao.insertUserCollection(dto);
		
	}

}
