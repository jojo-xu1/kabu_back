package com.kabu.dev.vo;

public class ParamObject {
	
	private String basedate; //基准日
	private double MinRate;	//均线的角度最小值
	private double MaxRate;//均线的角度最大值
	private double rateParam; //波浪下降的仰角
	private long AveDays; //
	
	public String getBasedate() {
		return basedate;
	}
	public void setBasedate(String basedate) {
		this.basedate = basedate;
	}
	public double getMinRate() {
		return MinRate;
	}
	public void setMinRate(double minRate) {
		MinRate = minRate;
	}
	public double getMaxRate() {
		return MaxRate;
	}
	public void setMaxRate(double maxRate) {
		MaxRate = maxRate;
	}
	public double getRateParam() {
		return rateParam;
	}
	public void setRateParam(double rateParam) {
		this.rateParam = rateParam;
	}
	public long getAveDays() {
		return AveDays;
	}
	public void setAveDays(long aveDays) {
		AveDays = aveDays;
	}
	
	
	
}
