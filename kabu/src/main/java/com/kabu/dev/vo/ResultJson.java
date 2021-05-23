package com.kabu.dev.vo;

import com.kabu.dev.exception.RichTextException;

public class ResultJson {
	private String code;
	private String message;
	private Object data;
	
	//Luke Zhou
	public ResultJson() {
		
	}
	public ResultJson(String message) {
		
		this.message = message ;
		
	}
	
	public static final ResultJson success(Object data) {
		ResultJson result=new ResultJson();
		result.setCode("200");
		result.setMessage("success!");
		result.setData(data);
		return result;
	}
	
	//Luke Zhou
	public static final ResultJson passwordWrong() {
		ResultJson result=new ResultJson();
		result.setCode("9999");
		result.setMessage("password is wrong");
		result.setData(null);
		return result;
	}
	
	public static final ResultJson fail(Exception e) {
		ResultJson result=new ResultJson();
		result.setCode("500");
		result.setMessage("しばらくお待ちください。");
		result.setData(null);
		return result;
	}
	
	public static final ResultJson fail(RichTextException e) {
		ResultJson result=new ResultJson();
		result.setCode("500");
		result.setMessage(e.getMessage());
		result.setData(null);
		return result;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
