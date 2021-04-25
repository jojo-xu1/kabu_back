package com.kabu.dev.dao;

import java.util.List;
import java.util.Map;

import com.kabu.dev.dto.KabuVideoInDto;
import com.kabu.dev.dto.KabuVideoOutDto;
import com.kabu.dev.entity.VideoEntity;

public interface VideoEntityMapper {
    int deleteByPrimaryKey(Long infoId);

    int insert(KabuVideoInDto record);

    VideoEntity selectByPrimaryKey(Long infoId);

    int updateByPrimaryKey(Map<String,Object> record);
    
    List<KabuVideoOutDto> selectList(String key);
    
    List<KabuVideoOutDto> selectListAll();

}