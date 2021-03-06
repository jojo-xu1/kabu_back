package com.kabu.dev.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dao.StockEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.StockOutDto;
import com.kabu.dev.service.StockMasterService;


@Service
public class StockMasterServiceImpl implements StockMasterService {

	@Autowired
	DailyEntityMapper dailyDao;
	@Autowired
	StockEntityMapper stockDao;
	

	@Override
	public Map<String, Object> queryDailyList(String num, String size, String dailyId, String flag) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(Integer.parseInt(num), Integer.parseInt(size));
		List<DailyOutDto> listLow = dailyDao.selectFromStockTradeLow();
		List<DailyOutDto> listMid = dailyDao.selectFromStockTradeMid();
		List<DailyOutDto> listHigh = dailyDao.selectFromStockTradeHigh();
		PageInfo<DailyOutDto> page = new PageInfo<>(listMid);
		map.put("listMed", listMid);
		map.put("listLow", listLow);
		map.put("listHigh", listHigh);
		map.put("pageSize", size);
		map.put("num", num);
		map.put("count", page.getTotal());
		map.put("TotalPages", page.getPages());
		return map;
	}

	@Override
	public StockOutDto queryStockInfo(String stockId) throws Exception {
		StockOutDto dto = stockDao.selectByPrimaryKey(stockId);
		return dto;
	}

}
