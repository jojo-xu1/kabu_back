package com.kabu.dev.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kabu.dev.dao.VideoEntityMapper;
import com.kabu.dev.dto.KabuVideoInDto;
import com.kabu.dev.dto.KabuVideoOutDto;
import com.kabu.dev.entity.VideoEntity;
import com.kabu.dev.service.KabuVideoService;
@Service("KabuVideoService")
public class KabuVideoServiceImpl implements KabuVideoService{
	
	@Autowired
	VideoEntityMapper videoDao;
	
	

	@Override
	public Map<String, Object> listAllKabuVideo(String num,String pageSize) throws Exception {
		Map<String, Object> map =new HashMap<String, Object>();
		PageHelper.startPage(Integer.parseInt(num),Integer.parseInt(pageSize));
		List<KabuVideoOutDto> list=videoDao.selectListAll();
		PageInfo<KabuVideoOutDto> page = new PageInfo<>(list);
		map.put("list",list);
		map.put("pageSize", pageSize);
		map.put("num",num);
		map.put("count", page.getTotal());
		map.put("TotalPages",page.getPages());
		return map;
	}
	
	@Override
	public Map<String, Object> queryKabuVideo(String id) throws Exception {
		Map<String, Object> map =new HashMap<String, Object>();
		//Todo modify
		VideoEntity entity = videoDao.selectByPrimaryKey(Long.parseLong(id));
		map.put("videoCd", entity.getVideoCd());
		map.put("path", entity.getPath());
		map.put("videoName",entity.getVideoName());
		return map;
	}

	@Override
	public Map<String, Object> queryListKabuVideo(String keyWord) throws Exception {
		Map<String, Object> map =new HashMap<String, Object>();
		//Todo modify
//		PageHelper.startPage(Integer.parseInt(num),Integer.parseInt(pageSize));
//		List<RichTextOutDto> list=richDao.selectList(columnId);
//		PageInfo<RichTextOutDto> page = new PageInfo<>(list);
//		System.out.println(list.size());
//		map.put("list",list);
//		map.put("pageSize", pageSize);
//		map.put("num",num);
//		map.put("count", page.getTotal());
//		map.put("TotalPages",page.getPages());
		return map;
	}

	@Override
	public void createKabuVideo(KabuVideoInDto recode) throws Exception {
		videoDao.insert(recode);
		
	}


}
