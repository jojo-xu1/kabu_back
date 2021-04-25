package com.kabu.dev.dto;

public class DailyOutDto  {
	
	private StockOutDto stock;

    private String dayId;
    
    private Long startPrice;

    private Long endPrice;

    private Long highPrice;

    private Long lowPrice;

    private Long vol;
    
    private Long ma10;
    
    private Long ma20;
    
    private double ma10UpRate;
    
    private double ma20UpRate;

	

	public StockOutDto getStock() {
		return stock;
	}

	public void setStock(StockOutDto stock) {
		this.stock = stock;
	}

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

	public Long getMa10() {
		return ma10;
	}

	public void setMa10(Long ma10) {
		this.ma10 = ma10;
	}

	public Long getMa20() {
		return ma20;
	}

	public void setMa20(Long ma20) {
		this.ma20 = ma20;
	}

	public double getMa10UpRate() {
		return ma10UpRate;
	}

	public void setMa10UpRate(double ma10UpRate) {
		this.ma10UpRate = ma10UpRate;
	}

	public double getMa20UpRate() {
		return ma20UpRate;
	}

	public void setMa20UpRate(double ma20UpRate) {
		this.ma20UpRate = ma20UpRate;
	}

    

}