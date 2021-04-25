package com.kabu.dev.dto;

import java.util.List;

public class KLineOutDto {
    private String stockId;

    private String stockName;
    
    private List<KLineDailyOutDto> kLineDailyOutDto;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<KLineDailyOutDto> getkLineDailyOutDto() {
		return kLineDailyOutDto;
	}

	public void setkLineDailyOutDto(List<KLineDailyOutDto> kLineDailyOutDto) {
		this.kLineDailyOutDto = kLineDailyOutDto;
	}
    
    
}
