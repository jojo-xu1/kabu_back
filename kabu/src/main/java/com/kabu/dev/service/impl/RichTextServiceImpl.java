package com.kabu.dev.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kabu.dev.dao.InfoEntityMapper;
import com.kabu.dev.dto.RichTextInDto;
import com.kabu.dev.dto.RichTextOutDto;
import com.kabu.dev.entity.InfoEntity;
import com.kabu.dev.service.RichTextService;
@Service("richTextService")
public class RichTextServiceImpl implements RichTextService{
	
	@Autowired
	InfoEntityMapper richDao;
	
	

	@Override
	public String modfiyRichText(String id) throws Exception {
		
		return null;
	}

	@Override
	public Map<String, Object> queryRichText(String id) throws Exception {
		Map<String, Object> map =new HashMap<String, Object>();
		InfoEntity entity = richDao.selectByPrimaryKey(Long.parseLong(id));
		map.put("infoId", entity.getInfoId());
		map.put("columnId", entity.getColumnId());
		map.put("stockId",entity.getStockId());
		map.put("imageUrl", entity.getImageUrl());
		map.put("link", entity.getLink());
		map.put("title", entity.getTitle());
		map.put("commentsCount",entity.getCommentsCount());
		map.put("viewsCount", entity.getViewsCount());
		map.put("publishedAt", entity.getPublishedAt());
		map.put("authorName", entity.getAuthorName());
		map.put("postId",entity.getPostId());
		map.put("contents", entity.getContents());
		return map;
	}

	@Override
	public Map<String, Object> queryListRichText(String columnId,String num,String size) throws Exception {
	
		Map<String, Object> map =new HashMap<String, Object>();
		PageHelper.startPage(Integer.parseInt(num),Integer.parseInt(size));
		List<RichTextOutDto> list=richDao.selectList(columnId);
		PageInfo<RichTextOutDto> page = new PageInfo<>(list);
		System.out.println(list.size());
		map.put("list",list);
		map.put("pageSize", size);
		map.put("num",num);
		map.put("count", page.getTotal());
		map.put("TotalPages",page.getPages());
		return map;
	}

	@Override
	public void createRichText(RichTextInDto dto) throws Exception {
		richDao.insert(dto);
		
	}


}
