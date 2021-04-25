package com.kabu.dev.service;

import java.util.Map;

import com.kabu.dev.dto.KabuVideoInDto;

public interface KabuVideoService {
	void createKabuVideo(KabuVideoInDto dto) throws Exception;
	Map<String,Object> listAllKabuVideo(String num,String pageSize) throws Exception;
	Map<String,Object> queryKabuVideo(String id) throws Exception;
	Map<String,Object> queryListKabuVideo(String keyWord) throws Exception;
}
