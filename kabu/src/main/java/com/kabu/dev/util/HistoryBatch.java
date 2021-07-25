package com.kabu.dev.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kabu.dev.dao.HistoryTradeEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.StockHistoryTradeDto;
import com.kabu.dev.dto.StockTradeDto;

@Component
public class HistoryBatch {

	@Autowired
	HistoryTradeEntityMapper DailyTradeDao;
	@Autowired
	DailyFilterHistory filter;
	private String dateNowStr;
	private String dateNowEnd;
	/**
	 * 历史股票推荐batch
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	
	
	public void Dailybatch() throws Exception { 
		//设定日期
		 dateNowStr = "20200801";  
		 dateNowEnd  =  "20200805"; 
		 
		 DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
		 DateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		 Date dateStr = fmt.parse(dateNowStr);
		 Date dateEnd = fmt.parse(dateNowEnd);
		 
		 Date current = dateStr;
		 while (current.before(dateEnd)) {
		     String basedate = sdf.format(current);
			//删除临时表单
			 DailyTradeDao.deletetempstocktrade();
			 //计算当日高风险股票
			 DailyByMA(basedate);//by MA10 ,MA20
			 //更新最终交易价格
			 DailyTradeDao.updateprice(basedate);
			//更新交易结束日期
			 DailyTradeDao.updateStockTradeDate(basedate);	//type =2
			 //卖掉收益率大于2%的股票
			 DailyTradeDao.updateStockDate();
			 //既往推荐股票的 updateflag=1
			 DailyTradeDao.updateStockTradeUpdateFlag();
			 //插入当日新增推荐股票
			 insertIntoStockTrade(basedate);
			 //日期累加
		     Calendar calendar = Calendar.getInstance();
		     calendar.setTime(current);
		     calendar.add(Calendar.DATE, 1);
		     current = calendar.getTime();
		        
		        System.out.println(sdf.format(current));
		    }

		 

		System.out.println("come to Dailybatch");
	}
	
	
	private void DailyByMA(String dateNowStr) throws Exception { 
			//获取当日推荐股票
			List<DailyOutDto> list = DailyTradeDao.selectstockpool(dateNowStr);
			List<DailyOutDto> listToday = filter.setMA(list,dateNowStr);
		for (int j=0;j<listToday.size();j++) {
			//插入新数据
			String stockId = listToday.get(j).getStock().getStockId();
			BigDecimal price =listToday.get(j).getEndPrice();
			StockTradeDto stockTradeDto = new StockTradeDto();
			stockTradeDto.setStockId(stockId);
			stockTradeDto.setType(2);
			stockTradeDto.setStartbuydate(dateNowStr);
			stockTradeDto.setBuy_price(price);
			stockTradeDto.setSell_price(null);
			stockTradeDto.setEndselldate("");
			stockTradeDto.setUpdateflag(0);
			DailyTradeDao.inserttempstocktrade(stockTradeDto);
		}
	}
	

	private void insertIntoStockTrade(String dateNowStr) throws Exception {
			//获取今日份推荐股票
			List<StockTradeDto> listToday = DailyTradeDao.selectFromTempStockTrade();
		for (int j=0;j<listToday.size();j++) {
			//插入新数据
			StockHistoryTradeDto stockHistoryTradeDto = new StockHistoryTradeDto();
			stockHistoryTradeDto.setStockId(listToday.get(j).getStockId());
			stockHistoryTradeDto.setType(listToday.get(j).getType());
			stockHistoryTradeDto.setBasedate(dateNowStr);
			stockHistoryTradeDto.setStartbuydate(listToday.get(j).getStartbuydate());
			stockHistoryTradeDto.setBuy_price(listToday.get(j).getBuy_price());
			stockHistoryTradeDto.setSell_price(listToday.get(j).getSell_price());
			stockHistoryTradeDto.setEndselldate(listToday.get(j).getEndselldate());
			stockHistoryTradeDto.setUpdateflag(0);
			DailyTradeDao.insertstocktrade(stockHistoryTradeDto);
		}
	}
}