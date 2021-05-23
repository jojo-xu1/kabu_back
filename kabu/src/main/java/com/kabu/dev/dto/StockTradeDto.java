package com.kabu.dev.dto;

public class StockTradeDto {
	private String stockId;
	private String startbuydate;
	private String endbuydate;
	private String startselldate;
	private String endselldate;
	private String updateflag;
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getStartbuydate() {
		return startbuydate;
	}
	public void setStartbuydate(String startbuydate) {
		this.startbuydate = startbuydate;
	}
	public String getEndbuydate() {
		return endbuydate;
	}
	public void setEndbuydate(String endbuydate) {
		this.endbuydate = endbuydate;
	}
	public String getStartselldate() {
		return startselldate;
	}
	public void setStartselldate(String startselldate) {
		this.startselldate = startselldate;
	}
	public String getEndselldate() {
		return endselldate;
	}
	public void setEndselldate(String endselldate) {
		this.endselldate = endselldate;
	}
	public String getUpdateflag() {
		return updateflag;
	}
	public void setUpdateflag(String updateflag) {
		this.updateflag = updateflag;
	}
}
