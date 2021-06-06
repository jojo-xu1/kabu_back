package com.kabu.dev.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
@Component
public class DailyFilter {
	@Autowired
	DailyEntityMapper dailyDao;

	/**
	 * 计算10日均值、20日均值、10日均值增长率、20日均值增长率并筛选
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<DailyOutDto> setMA(List<DailyOutDto> list) throws Exception {
		List<DailyOutDto> rtnList = new ArrayList<DailyOutDto>();
		//假定参数
		long AveDays = 3;
		long minRate = 5;  //增长率的1000倍值
		long maxRate = 10;	//增长率的1000倍值
		//当抽出数据大于10条时，缩紧抽出条件
		do {
			rtnList = getList(list, AveDays, minRate, maxRate);
			minRate = minRate+1;
		}while(rtnList.size()>10);
		//TODO 筛选后0条数据
		return rtnList;
	}
	
	public List<DailyOutDto> getList(List<DailyOutDto> list,Long AveDays,Long minRate,Long maxRate) throws Exception {
		List<DailyOutDto> kabuList = new ArrayList<DailyOutDto>();
		
		for (int i = list.size() - 1; i >= 0; i--) {
			//获取股票数据
			if(list.get(i).getStock() == null) {
			}else {
			List<DailyOutDto> DailyPriceList = dailyDao.selectByIdMini(list.get(i).getStock().getStockId());
			
			List<Double> tenDayAveList = new ArrayList<>(); //十日均线值list
			List<Double> twnDayAveList = new ArrayList<>(); //二十日均线值list			
			List<Double> twnUpRateList = new ArrayList<>(); //二十日均线值增长率list
			
			//条件一：可参考数据大于需要条数
			if(DailyPriceList.size() < (20 + AveDays)) {
				continue;//TODO 当可参考数据小于需要条数时的算法
			}
			//计算十日均线  二十日均线
			for (int k=0;k<AveDays;k++) {
				//设定初始值
				double twnDayAve =0;
				double tenDayAve = 0;
				
				for(int j =k;j<k+10;j++) {
					tenDayAve = (DailyPriceList.get(j).getEndPrice().doubleValue()+ tenDayAve*(j-k))/(j-k+1);
				}
				for(int j =k;j<k+20;j++) {
					twnDayAve = (DailyPriceList.get(j).getEndPrice().doubleValue()+ twnDayAve*(j-k))/(j-k+1);
				}
				tenDayAveList.add(tenDayAve);
				twnDayAveList.add(twnDayAve);						
			}
			
			//二十日均线 均线变化率 
			for (int k=0;k<AveDays-1;k++) {
				double rate20 = (twnDayAveList.get(k)-twnDayAveList.get(k+1))*1000/twnDayAveList.get(k+1);
				twnUpRateList.add(rate20);
			}
			
			//条件二：十日均线和二十日均线走势向上   ※可以省略
			if(tenDayAveList.get(0)<tenDayAveList.get(1) || twnDayAveList.get(0)<twnDayAveList.get(1)) {
				continue;
			}
			boolean flag = false;
			for (int k=0;k<AveDays;k++) {
				
				//条件三：10日线连续在20日线之上
				if(tenDayAveList.get(k)<tenDayAveList.get(k)) {
					flag = true;
					break;
					
				}	
				
				//条件四：二十日均线在minRate到maxRate之间 
				if(k<AveDays-1) {
					if(twnUpRateList.get(k) < minRate ||twnUpRateList.get(k)>maxRate) {
						flag = true;
						break;
					}
				}
			}
			
			if(flag == true) {
				continue;
			}


			DailyOutDto rtnDto = list.get(i);
			rtnDto.setMa10((Double) tenDayAveList.get(0)); // 10日均值
			rtnDto.setMa20((Double) twnDayAveList.get(0)); // 20日均值
			rtnDto.setMa20UpRate(twnUpRateList.get(0)); // 20日均值变化率
			kabuList.add(rtnDto);
			
		}
	}
		return kabuList;
	}

	public List<DailyOutDto> setMAHigh(List<DailyOutDto> list) throws Exception {
		List<DailyOutDto> rtnList = new ArrayList<DailyOutDto>();
		//假定参数
		long AveDays = 3;
		long minRate = 5;  //增长率的1000倍值
		long maxRate = 10;	//增长率的1000倍值
		//当抽出数据大于10条时，缩紧抽出条件
		do {
			rtnList = getList(list, AveDays, minRate, maxRate);
			minRate = minRate+1;
		}while(rtnList.size()>10);
		//TODO 筛选后0条数据
		return rtnList;
	}
	
}