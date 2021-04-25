package com.kabu.dev.service;

import java.util.Map;

import com.kabu.dev.dto.RichTextInDto;

public interface RichTextService {
	void createRichText(RichTextInDto dto) throws Exception;
	String modfiyRichText(String id) throws Exception;
	Map<String,Object> queryRichText(String id) throws Exception;
	Map<String,Object> queryListRichText(String columnId,String num,String size) throws Exception;
}
