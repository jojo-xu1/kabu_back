package com.kabu.dev.dao;

import java.util.Map;

import com.kabu.dev.dto.StockOutDto;
import com.kabu.dev.entity.StockEntity;

public interface StockEntityMapper {
    int deleteByPrimaryKey(String stockId);

    int insert(StockEntity record);

    StockOutDto selectByPrimaryKey(String stockId);

    int updateByPrimaryKeySelective(Map<String,Object> record,String stockId);
    
    
}