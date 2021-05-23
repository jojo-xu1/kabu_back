package com.kabu.dev.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kabu.dev.dto.KabuVideoInDto;

public interface KabuUserService {
	public void qqq();
	String queryKabuUser(String user_id) throws Exception;
	Map<String,Object> queryUserDetails(String user_id) throws Exception;
	public void createUser(String user_id, String password);
	public void updateUser(String user_id, String password);
}
