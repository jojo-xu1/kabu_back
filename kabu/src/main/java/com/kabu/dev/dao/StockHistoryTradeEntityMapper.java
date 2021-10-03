package com.kabu.dev.dao;

import java.util.List;

import com.kabu.dev.entity.StockHistoryTradeEntity;

public interface StockHistoryTradeEntityMapper {

    List<StockHistoryTradeEntity> selectAll();
    void truncateTable();
}