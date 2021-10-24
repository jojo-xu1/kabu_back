package com.kabu.dev.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kabu.dev.dao.StockHistoryTradeEntityMapper;
import com.kabu.dev.entity.StockHistoryTradeEntity;

@Component
public class ProfitWinRateBatch {
	
	@Autowired
	StockHistoryTradeEntityMapper shtMapper;
	
	private final BigDecimal round = new BigDecimal(-0.3);
	
	public Map<String,Double> getResult(){
		
		BigDecimal profit=new BigDecimal(0);
		int win=0;
		int count=0;
		Map<String,Double> map =new HashMap<>();
		List<StockHistoryTradeEntity> list=shtMapper.selectAll();
		
		list=list.stream().filter(entity -> {
			if(entity.getBuyPrice()!=null&&entity.getSellPrice()!=null)
				if(entity.getBuyPrice()>0 && entity.getSellPrice()>0)
					return true;
			return false;
		}).collect(Collectors.toList());
		
		for(StockHistoryTradeEntity entity:list) {
			BigDecimal buy=new BigDecimal(entity.getBuyPrice());
			BigDecimal sell=new BigDecimal(entity.getSellPrice());
			BigDecimal r=sell.subtract(buy).divide(buy,10,BigDecimal.ROUND_HALF_UP);
			
			if(r.compareTo(round)==1) {
				System.out.println(r);
				profit=profit.add(r);
				count++;
				if(r.doubleValue()>0) {
					win++;
				}
			}
		}
		BigDecimal countB = new BigDecimal(count);
		System.out.println("count:------"+count);
		System.out.println("win:------"+win);
		System.out.println("profit:------"+profit);
		if(win!=0) {
			map.put("winRate", new BigDecimal(win).divide(countB,6,BigDecimal.ROUND_HALF_UP).doubleValue());	
		}else {
			map.put("winRate",Double.parseDouble("0"));
		}
		if(!profit.equals(BigDecimal.ZERO)) {
		map.put("profitRate", profit.divide(countB,6,BigDecimal.ROUND_HALF_UP).doubleValue());
		}else {
			map.put("profitRate",Double.parseDouble("0"));
		}
		shtMapper.truncateTable();
		System.out.println(map);
		return map;
	}
}
