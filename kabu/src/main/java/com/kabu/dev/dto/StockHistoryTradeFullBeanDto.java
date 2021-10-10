package com.kabu.dev.dto;

import java.math.BigDecimal;
import java.util.Date;

public class StockHistoryTradeFullBeanDto {
	private String tradeId;
	private String stockId;
	private int type;
	private String basedate;
	private String startbuydate;
	private BigDecimal buy_price;
	private BigDecimal sell_price;
	private BigDecimal today_price;
	private String endselldate;
	private int updateflag;
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public BigDecimal getToday_price() {
		return today_price;
	}
	public void setToday_price(BigDecimal today_price) {
		this.today_price = today_price;
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
	
}
