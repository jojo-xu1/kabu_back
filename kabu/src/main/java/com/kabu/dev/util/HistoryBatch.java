package com.kabu.dev.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kabu.dev.dao.HistoryTradeEntityMapper;
import com.kabu.dev.dao.StockHistoryTradeEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
import com.kabu.dev.dto.StockHistoryTradeDto;
import com.kabu.dev.dto.StockHistoryTradeFullBeanDto;
import com.kabu.dev.dto.StockTradeDto;
import com.kabu.dev.vo.ParamObject;

@Component
public class HistoryBatch {

	@Autowired
	HistoryTradeEntityMapper DailyTradeDao;
	
	@Autowired
	ProfitWinRateBatch profitWinRateBatch;
	
	@Autowired
	StockHistoryTradeEntityMapper shtMapper;
	
	@Autowired
	DailyFilterHistory filter;
	private double MinRate;
	private double MaxRate;
	private String dateNowStr;
	private String dateNowEnd;
	private double rateParam;
	private long AveDays; 
	
	/**
	 * 历史股票推荐batch
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	
	
	public void Dailybatch() throws Exception { 
		//读取csv
		Charset charset = StandardCharsets.UTF_8;
		int bufferSize = 5 * 1024 * 1024;
		String tempPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println("读取的路径"+tempPath);
		List<String> OnePath = Arrays.asList(tempPath.split("/"));
		List dataPath = new ArrayList(OnePath);
		 dataPath.remove(0);
		// Collections.replaceAll(dataPath, "classes!", "classes");
		 for (int i = 1;i<dataPath.size();i++) {
			 
			 if (dataPath.get(i).equals("kabu-0.0.1-SNAPSHOT.jar!")) {
				// System.out.println("删除元素"+dataPath.get(i));
				 dataPath.remove(i);
			 }
			 if (dataPath.get(i).equals("BOOT-INF")) {
				// System.out.println("删除元素"+dataPath.get(i));
				 dataPath.remove(i);
			 }
			 if (dataPath.get(i).equals("classes!")) {
				 dataPath.remove(i);
			 }
		 }
		 //System.out.println("数组"+dataPath);
		
		
		String path= String.join("/", dataPath);
		//System.out.println("最终路径"+path);
		List<String[]> data = new ArrayList<>();
		File writeFile = new File("/"+path+"/"+"output.csv");
		BufferedWriter writeText = new BufferedWriter( new FileWriter(writeFile,true));
		writeText.write("minrate,maxrate,stardate,enddate,winRate,profitRate");
		//writeText.close();
		try (BufferedReader reader = new BufferedReader(
		        new InputStreamReader(new FileInputStream(new File("/"+path+"/"+"input.csv")), charset), bufferSize)) {
		    String line;
		    while (Objects.nonNull(line = reader.readLine())) {
		        data.add(line.split(","));
		    }

			//清空数据库的数据
			shtMapper.truncateTable();
			
		    for (int i = 1;i<data.size();i++) {
		    	
				 MinRate = Double.valueOf(data.get(i)[0]);
				 MaxRate = Double.valueOf(data.get(i)[1]);
				 dateNowStr = data.get(i)[2];  
				 dateNowEnd  =  data.get(i)[3]; 
				 rateParam  = Double.valueOf(data.get(i)[4]); 
				 AveDays   = Long.valueOf(data.get(i)[5]); 
				 
				 DateFormat fmt =new SimpleDateFormat("yyyyMMdd");
				 DateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
				 Date dateStr = fmt.parse(dateNowStr);
				 Date dateEnd = fmt.parse(dateNowEnd);
				 
				 Date current = dateStr;
				 while (current.before(dateEnd)) {
					 String basedate = sdf.format(current);
					 //查询当天株数
					 int stockList = DailyTradeDao.selectFromRealstock(basedate);
					 if (stockList == 0) {
						//日期累加
						 Calendar calendar = Calendar.getInstance();
					     calendar.setTime(current);
					     calendar.add(Calendar.DATE, 1);
					     current = calendar.getTime();
					     //跳出循环
						 continue;
					 }
					 ParamObject paramObject = new ParamObject();
					 paramObject.setBasedate(basedate);
					 paramObject.setMaxRate(MaxRate);
					 paramObject.setMinRate(MinRate);
					 paramObject.setRateParam(rateParam);
					 paramObject.setAveDays(AveDays);
					 
					//删除临时表单
					 DailyTradeDao.deletetempstocktrade();
					//计算当日低风险股票
					 //DailyByMA1(basedate);
					//计算当日中风险股票
					// DailyByMA3(basedate);
					 //计算当日高风险股票
					 DailyByMA2(paramObject);
					 //更新最终交易价格
					 DailyTradeDao.updateprice(basedate);
					 //均线拐头向下的时候卖出
					 DailySellMA2(basedate);
					 //更新持有天数大于10的股票
					 DailyTradeDao.updateEndBy10Day(basedate);
					//如果选择的股票不再出现在今天的List中就卖掉
					// DailyTradeDao.updateStockTradeDate(basedate);	 //type=2
					 //卖掉收益率大于2%的股票
					 //DailyTradeDao.updateStockDate(basedate); //type=2
					 //既往推荐股票的 updateflag=1
					 DailyTradeDao.updateStockTradeUpdateFlag();
					 //删除sellprice=0的股票
					// DailyTradeDao.deletePriceZero();	
					 //插入当日新增推荐股票
					 insertIntoStockTrade(basedate);
					 //日期累加
				     Calendar calendar = Calendar.getInstance();
				     calendar.setTime(current);
				     calendar.add(Calendar.DATE, 1);
				     current = calendar.getTime();
				        
				        System.out.println(sdf.format(current));
				    }
				 
		//输出本次计算元数据
				 
				 File fileDetail = new File("/"+path+"/"+"Detail_"+i+".csv");
				 BufferedWriter DetailText = new BufferedWriter( new FileWriter(fileDetail));
				 DetailText.write("trade_id,stock_id,type,basedate,startbuydate,buy_price,sell_price,today_price,endselldate,updateflag");
				 //获取表中内容
				 List<StockHistoryTradeFullBeanDto> selectAll = DailyTradeDao.selectAllStockHistoryTrade();
				 //循环写进文件
				 if(!selectAll.isEmpty()) {
					 for(int j=0;j<selectAll.size();j++) {
						 DetailText.newLine();
						 DetailText.write(selectAll.get(j).getTradeId()+","+selectAll.get(j).getStockId()+","+selectAll.get(j).getType()+","+selectAll.get(j).getBasedate()+","
						 +selectAll.get(j).getStartbuydate()+","+selectAll.get(j).getBuy_price()+","+selectAll.get(j).getSell_price()+","+selectAll.get(j).getToday_price()+","+selectAll.get(j).getEndselldate()
						 +","+selectAll.get(j).getUpdateflag());
						   
					 } 
				 }
				 DetailText.close();
		//调batch处理文件 
				Map<String,Double> A = profitWinRateBatch.getResult();
				//BufferedWriter writeText1 = new BufferedWriter( new FileWriter(writeFile,true));
				 writeText.newLine();
				 writeText.write(MinRate+","+MaxRate+","+dateNowStr+","+dateNowEnd+","+A.get("winRate")+","+A.get("profitRate"));
				    }
		  
		} catch (Exception e) {
		    // IOException 就囊括了读取文件可能发生的全部意外
		    e.printStackTrace();
		}
		writeText.flush();
		writeText.close();
		System.out.println("come to Dailybatch");
	}
	
	private void DailyByMA1(String dateNowStr) throws Exception {
		List<DailyOutDto> listTodayLow= DailyTradeDao.selectstockpoollow(dateNowStr);
		for (int j=0;j<listTodayLow.size();j++) {
			//插入新数据
			if(listTodayLow.get(j).getStock()==null) {
				continue;
			}
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
	
	private void DailyByMA2(ParamObject paramObject) throws Exception { 
			//获取当日推荐股票
			List<DailyOutDto> list = DailyTradeDao.selectstockpool(paramObject.getBasedate());
			List<DailyOutDto> listToday = filter.setMA(list,paramObject);
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
	private void DailyByMA3(String dateNowStr) throws Exception { 
		//获取今日份推荐股票
		List<DailyOutDto> list = DailyTradeDao.selectstockpoolhigh(dateNowStr);
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
	private void DailySellMA2(String dateNowStr) throws Exception { 
		//获取当日推荐股票
		long AveDays = 3;
		List<DailyOutDto> list = DailyTradeDao.selectFromHistoryStockTrade(dateNowStr);
		if(list.size()<=0)return;
		List<DailyOutDto> listToday = filter.getSellList(list,AveDays,dateNowStr);
		for (int j=0;j<listToday.size();j++) {
		//更新卖出数据
		String stockId = listToday.get(j).getStock().getStockId();
		DailyTradeDao.updateEndByMoveLineDown(dateNowStr,stockId);
		
	}
}
}
