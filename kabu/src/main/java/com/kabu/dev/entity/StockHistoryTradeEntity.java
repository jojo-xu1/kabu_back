package com.kabu.dev.entity;

public class StockHistoryTradeEntity {

    private Long buyPrice;

    private Long sellPrice;


    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

}