package com.kabu.dev.dto;

public class KLineDailyOutDto {
	
	private String dayId;
	
	private Long startPrice;
	
	private Long endPrice;
	
	private Long highPrice;
	
	private Long lowPrice;
	
	private Long vol;

	public String getDayId() {
		return dayId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
	}

	public Long getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Long startPrice) {
		this.startPrice = startPrice;
	}

	public Long getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Long endPrice) {
		this.endPrice = endPrice;
	}

	public Long getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Long highPrice) {
		this.highPrice = highPrice;
	}

	public Long getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Long lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Long getVol() {
		return vol;
	}

	public void setVol(Long vol) {
		this.vol = vol;
	}
	
}
