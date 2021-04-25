package com.kabu.dev.dao;

import java.util.List;
import java.util.Map;

import com.kabu.dev.dto.RichTextInDto;
import com.kabu.dev.dto.RichTextOutDto;
import com.kabu.dev.entity.InfoEntity;

public interface InfoEntityMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(RichTextInDto dto);

    InfoEntity selectByPrimaryKey(Long infoId);

    int updateByPrimaryKey(Map<String,Object> record,Long infoId);
    
    List<RichTextOutDto> selectList(String columnId);

}