package com.kabu.dev.dto;

import java.math.BigDecimal;

public class StockHistoryTradeDto {
	private String stockId;
	private String basedate;
	private String startbuydate;
	private int type;
	private BigDecimal buy_price;
	private BigDecimal sell_price;
	private String endselldate;
	private int updateflag;
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	public String getBasedate() {
		return basedate;
	}
	public void setBasedate(String basedate) {
		this.basedate = basedate;
	}
	public String getStartbuydate() {
		return startbuydate;
	}
	public void setStartbuydate(String startbuydate) {
		this.startbuydate = startbuydate;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getEndselldate() {
		return endselldate;
	}
	public void setEndselldate(String endselldate) {
		this.endselldate = endselldate;
	}
	public int getUpdateflag() {
		return updateflag;
	}
	public void setUpdateflag(int updateflag) {
		this.updateflag = updateflag;
	}
	public BigDecimal getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(BigDecimal buy_price) {
		this.buy_price = buy_price;
	}
	public BigDecimal getSell_price() {
		return sell_price;
	}
	public void setSell_price(BigDecimal sell_price) {
		this.sell_price = sell_price;
	}
}
