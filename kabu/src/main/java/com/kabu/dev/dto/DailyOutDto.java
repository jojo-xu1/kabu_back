package com.kabu.dev.dto;

import java.math.BigDecimal;

public class DailyOutDto  {
	
	private StockOutDto stock;

    private String dayId;
    
    private BigDecimal startPrice;

    private BigDecimal endPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private Long vol;
    
    private double ma10;
    
    private double ma20;
    
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

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Long getVol() {
		return vol;
	}

	public void setVol(Long vol) {
		this.vol = vol;
	}

	public double getMa10() {
		return ma10;
	}

	public void setMa10(double ma10) {
		this.ma10 = ma10;
	}

	public double getMa20() {
		return ma20;
	}

	public void setMa20(double ma20) {
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