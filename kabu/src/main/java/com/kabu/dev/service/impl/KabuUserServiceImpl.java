package com.kabu.dev.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kabu.dev.dao.UserEntityMapper;
import com.kabu.dev.dao.VideoEntityMapper;
import com.kabu.dev.dto.KabuVideoInDto;
import com.kabu.dev.dto.KabuVideoOutDto;
import com.kabu.dev.entity.UserEntity;
import com.kabu.dev.entity.VideoEntity;
import com.kabu.dev.service.KabuUserService;
@Service("KabuUserService")
public class KabuUserServiceImpl implements KabuUserService{
	
	@Autowired
	UserEntityMapper userDao;
	
	@Override
	public void qqq() {
		System.out.println();
	}
	
	@Override
	public String queryKabuUser(String user_id) throws Exception {

		String selectedPassword = userDao.selectPasswordByUser(user_id);

		return selectedPassword;
	}

	@Override
	public Map<String, Object> queryUserDetails(String user_id) throws Exception {
		Map<String, Object> map =new HashMap<String, Object>();
		//Todo modify
		UserEntity entity = userDao.selectUserDetailsByUser(user_id);
		map.put("id", entity.getId());
		map.put("userID", entity.getUserID());
		map.put("name", entity.getName());
		map.put("displayName", entity.getDisplayName());


		return map;
	}

	@Override
	public void createUser(String user_id, String password) {

		userDao.createUser(user_id,password);
		
	}

	@Override
	public void updateUser(String user_id, String password) {
		
		userDao.updateUser(user_id,password);
		
	}

}
