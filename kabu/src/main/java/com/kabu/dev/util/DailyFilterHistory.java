package com.kabu.dev.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kabu.dev.dao.DailyEntityMapper;
import com.kabu.dev.dto.DailyOutDto;
@Component
public class DailyFilterHistory {
	@Autowired
	DailyEntityMapper dailyDao;

	static private double GROUTH =12.0;
	static private double MINRATE =1.0;
	static private double MAXRATE =3.5;
	static private int DATA_20 =20;
	static private int DATA_10 =10;
	static private int DATA_80 =80;
	static private int DATA_60 =60;
	static private int MAX_STOCK =7;
	static private double DOB_10 =10.0;
	static private double DOB_20 =20.0;
	static private double DOB_60 =60.0;
	static private double DOB_80 =80.0;
	/**
	 * 计算10日均值、20日均值、10日均值增长率、20日均值增长率并筛选
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public List<DailyOutDto> setMA(List<DailyOutDto> list,String basedate) throws Exception {
		List<DailyOutDto> rtnList = new ArrayList<DailyOutDto>();
		//假定参数
		long AveDays = 3;
		long minRate = 2;  //增长率的1000倍值
		long maxRate = 5;	//增长率的1000倍值
		//当抽出数据大于10条时，缩紧抽出条件
		//do {
			rtnList = getList(list, AveDays, minRate, maxRate,basedate);
			minRate = minRate+1;
		//}while(rtnList.size()>10);
		//TODO 筛选后0条数据
		return rtnList;
	}
	
	public List<DailyOutDto> getList(List<DailyOutDto> list,Long AveDays,Long minRate,Long maxRate,String basedate) throws Exception {
		List<DailyOutDto> kabuList = new ArrayList<DailyOutDto>();
		int day = (DATA_20+AveDays.intValue())*2;
		int nodata =0;
		int stockno=0;
		int step1,step2,step3,step4,step5;
		step1= step2 = step3 = step4 = step5 = 0;
		System.out.println(list.size());
		for (int i = list.size() - 1; i >= 0; i--) {
			boolean aveerror=false;//成长超过一般
			//获取股票数据
			if(list.get(i).getStock() == null) {
			}else {
			List<DailyOutDto> DailyPriceList = dailyDao.selectHisListById(list.get(i).getStock().getStockId(),day,basedate);
			
			List<Double> tenDayAveList = new ArrayList<>(); //十日均线值list
			List<Double> twnDayAveList = new ArrayList<>(); //二十日均线值list			
			List<Double> twnUpRateList = new ArrayList<>(); //二十日均线值增长率list
			
			//条件一：可参考数据大于需要条数
			if(DailyPriceList.size() < day) {
				nodata++;
				continue;//TODO 当可参考数据小于需要条数时的算法
			}
			//计算十日均线  二十日均线
			stockno++;
			
			for (int k=0;k<DATA_10;k++) {//
				//设定初始值
				double twnDayAve =0;
				double tenDayAve = 0;
				double oldtwnDayAve=0;

				for(int j =k;j<k+DATA_10;j++) {
					//tenDayAve = (DailyPriceList.get(j).getEndPrice().doubleValue()+ tenDayAve*(j-k))/(j-k+1);
					tenDayAve += DailyPriceList.get(j).getEndPrice().doubleValue();
				}
				tenDayAve = tenDayAve/DOB_10;
				for(int j =k;j<k+DATA_20;j++) {
					//twnDayAve = (DailyPriceList.get(j).getEndPrice().doubleValue()+ twnDayAve*(j-k))/(j-k+1);
					twnDayAve += DailyPriceList.get(j).getEndPrice().doubleValue();
					/*if(j==k)oldtwnDayAve=twnDayAve;
					 BigDecimal newold = new BigDecimal(Math.abs(twnDayAve-oldtwnDayAve));
				     BigDecimal old = new BigDecimal(oldtwnDayAve);
			        if(oldtwnDayAve < 0.1 || newold.divide(old, 4, BigDecimal.ROUND_HALF_UP).doubleValue()*100 > GROUTH){
			        	aveerror=true;
			        	break;
			        };
			        oldtwnDayAve=twnDayAve;*/
				}
				twnDayAve = twnDayAve/DOB_20;
				if(aveerror)break;
				tenDayAveList.add(tenDayAve);
				twnDayAveList.add(twnDayAve);						
			}
			if(aveerror)continue;
			//二十日均线 均线变化率 
			for (int k=0;k<AveDays-1;k++) {
				double rate20 = (twnDayAveList.get(k)-twnDayAveList.get(k+1))*1000/twnDayAveList.get(k+1);
				twnUpRateList.add(rate20);
			}
			step1++;
			//条件二：十日均线和二十日均线走势向上   ※可以省略
			if(tenDayAveList.get(0)<tenDayAveList.get(1) || twnDayAveList.get(0)<twnDayAveList.get(1)) {
				continue;
			}
			if(twnDayAveList.get(DATA_10-2)<twnDayAveList.get(DATA_10-1)) {
				continue;
			}
			step2++;
			boolean flag = false;
			for (int k=0;k<AveDays;k++) {
				
				//条件三：二十日均线在minRate到maxRate之间 
				if(k<AveDays-1) {
					if(twnUpRateList.get(k) < MINRATE ||twnUpRateList.get(k)>MAXRATE) {
						flag = true;
						break;
					}
				}			
			}
			
			if(flag == true) {
				continue;
			}
			flag = false;
			step3++;
			//条件四：10日线连续在20日线之上
			for (int k=0;k<=AveDays;k++) {
				//条件四：10日线连续在20日线之上形成交叉
				if(tenDayAveList.get(k)<twnDayAveList.get(k)) {
					flag = true;
					break;
					
				}	
			}
			if(flag == true) {
				continue;
			}
			step4++;
			for (int k=DATA_10-1;k>=DATA_10-AveDays.intValue()-1;k--) {
				//条件四：10日线连续在20日线之上形成交叉
				if(tenDayAveList.get(k)>twnDayAveList.get(k)) {
					flag = true;
					break;
					
				}	
			}
			if(flag == true) {
				continue;
			}
			step5++;
			DailyOutDto rtnDto = list.get(i);
			rtnDto.setMa10((Double) tenDayAveList.get(0)); // 10日均值
			rtnDto.setMa20((Double) twnDayAveList.get(0)); // 20日均值
			rtnDto.setMa20UpRate(twnUpRateList.get(0)); // 20日均值变化率
			kabuList.add(rtnDto);
			if(kabuList.size()>MAX_STOCK)return kabuList;
		}
			
	}
		System.out.println("step1=");
		System.out.println(step1);
		System.out.println("step2=");
		System.out.println(step2);	
		System.out.println("step3=");
		System.out.println(step3);
		System.out.println("step4=");
		System.out.println(step4);	
		System.out.println("step5=");
		System.out.println(step5);
		return kabuList;
	}

	public List<DailyOutDto> setMAHigh(List<DailyOutDto> list) throws Exception {
		List<DailyOutDto> rtnList = new ArrayList<DailyOutDto>();
		//假定参数
		long AveDays = 10;
		long minRate = 5;  //增长率的1000倍值
		long maxRate = 10;	//增长率的1000倍值
		//当抽出数据大于10条时，缩紧抽出条件
		do {
			rtnList = getListHigh(list, AveDays, minRate, maxRate);
			minRate = minRate+1;
		}while(rtnList.size()>10);
		//TODO 筛选后0条数据
		return rtnList;
	}
	public List<DailyOutDto> getListHigh(List<DailyOutDto> list,Long AveDays,Long minRate,Long maxRate) throws Exception {
		List<DailyOutDto> kabuList = new ArrayList<DailyOutDto>();
		int day = DATA_80+AveDays.intValue()*2;
		for (int i = list.size() - 1; i >= 0; i--) {
			boolean aveerror=false;//成长超过一般
			//获取股票数据
			if(list.get(i).getStock() == null) {
			}else {
			List<DailyOutDto> DailyPriceList = dailyDao.selectByIdMiniEx(list.get(i).getStock().getStockId(),day);
			
			List<Double> sixtyDayAveList = new ArrayList<>(); //六十日均线值list
			List<Double> eightyDayAveList = new ArrayList<>(); //八十日均线值list			
			List<Double> eightyUpRateList = new ArrayList<>(); //八十日均线值增长率list
			
			//条件一：可参考数据大于需要条数
			if(DailyPriceList.size() < (DATA_80 + AveDays)) {
				continue;//TODO 当可参考数据小于需要条数时的算法
			}

			//计算六十日均线  八十日均线
			for (int k=0;k<AveDays;k++) {
				//设定初始值
				double eightyDayAve =0;
				double sixtyDayAve = 0;
				double oldEightyDayAve =0;
				for(int j =k;j<k+DATA_60;j++) {
//					sixtyDayAve = (DailyPriceList.get(j).getEndPrice().doubleValue()+ sixtyDayAve*(j-k))/(j-k+1);
					sixtyDayAve += DailyPriceList.get(j).getEndPrice().doubleValue();
				}
				sixtyDayAve = sixtyDayAve/DOB_60;
				for(int j =k;j<k+DATA_80;j++) {
//					eightyDayAve = (DailyPriceList.get(j).getEndPrice().doubleValue()+ eightyDayAve*(j-k))/(j-k+1);
					eightyDayAve += DailyPriceList.get(j).getEndPrice().doubleValue();
//					if(j==k)oldEightyDayAve=eightyDayAve;
//					 BigDecimal newold = new BigDecimal(Math.abs(eightyDayAve-oldEightyDayAve));
//				     BigDecimal old = new BigDecimal(oldEightyDayAve);
//			        if(oldEightyDayAve < 0.1 || newold.divide(old, 4, BigDecimal.ROUND_HALF_UP).doubleValue()*100 > GROUTH){
//			        	aveerror=true;
//			        	break;
//			        };
//			        oldEightyDayAve=eightyDayAve;
				}
				eightyDayAve = eightyDayAve/DOB_80;
				if(aveerror)break;
				sixtyDayAveList.add(sixtyDayAve);
				eightyDayAveList.add(eightyDayAve);						
			}
			if(aveerror)continue;
			//八十日均线 均线变化率 
			for (int k=0;k<AveDays-1;k++) {
				double rate80 = (eightyDayAveList.get(k)-eightyDayAveList.get(k+1))*1000/eightyDayAveList.get(k+1);
				eightyUpRateList.add(rate80);
			}
			
			//条件二：六十日均线和八十日均线走势向上   ※可以省略
			if(sixtyDayAveList.get(0)<sixtyDayAveList.get(1) || eightyDayAveList.get(0)<eightyDayAveList.get(1)) {
				continue;
			}
			if(eightyDayAveList.get(DATA_60-2)<eightyDayAveList.get(DATA_60-1)) {
				continue;
			}
			boolean flag = false;
			for (int k=0;k<AveDays;k++) {
				
				//条件三：60日线连续在80日线之上
				if(sixtyDayAveList.get(k)<eightyDayAveList.get(k)) {
					flag = true;
					break;
					
				}	
				
				//条件四：八十日均线在minRate到maxRate之间 
				if(k<AveDays-1) {
					if(eightyUpRateList.get(k) < minRate ||eightyUpRateList.get(k)>maxRate) {
						flag = true;
						break;
					}
				}
			}
			
			
			if(flag == true) {
				continue;
			}


			DailyOutDto rtnDto = list.get(i);
			rtnDto.setMa10((Double) sixtyDayAveList.get(0)); // 60日均值
			rtnDto.setMa20((Double) eightyDayAveList.get(0)); // 80日均值
			rtnDto.setMa20UpRate(eightyUpRateList.get(0)); // 80日均值变化率
			kabuList.add(rtnDto);
			
		}
	}
		return kabuList;
	}
	
}