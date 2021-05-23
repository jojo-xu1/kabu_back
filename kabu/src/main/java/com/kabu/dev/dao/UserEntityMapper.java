package com.kabu.dev.dao;

import java.util.List;
import java.util.Map;

import com.kabu.dev.dto.KabuVideoInDto;
import com.kabu.dev.dto.KabuVideoOutDto;
import com.kabu.dev.entity.UserEntity;

public interface UserEntityMapper {
    int deleteByPrimaryKey(Long infoId);
    
    String selectVideo_cd(String path);

    int insert(KabuVideoInDto record);
    
    int insertKey(KabuVideoInDto record);

    String selectPasswordByUser(String user_id);
    
    UserEntity selectUserDetailsByUser(String user_id);

    int updateByPrimaryKey(Map<String,Object> record);
    
    List<KabuVideoOutDto> selectList(String key);
    
    List<KabuVideoOutDto> selectListAll();
    
    List<KabuVideoOutDto> setInformation();

	void createUser(String user_id, String password);

	void updateUser(String user_id, String password);

}