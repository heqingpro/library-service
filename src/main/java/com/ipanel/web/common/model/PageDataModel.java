package com.ipanel.web.common.model;

import java.util.List;

public class PageDataModel {
	
	private int total;
	
	private List<?> rows;
	
	private String msg;
	
	private Integer code;
	
	public static Integer SUCCESS_CODE = 200; //成功
	
	public static Integer PRAMLOST_CODE = 201; //缺少参数
	
	public static Integer PARAM_ERROR_CODE = 403; // 参数异常
	
	public static Integer ERROR_CODE = 500; //成功
		
	public static String SUCCESS_MSG = "成功"; //成功
	
	public static String PARAM_ERROR_MSG = "参数异常"; // 参数异常
	
	public static String PRAMLOST_MSG = "缺少参数"; //缺少参数

	public static String ERROR_MSG = "服务器端错误"; //成功
	
	
	public PageDataModel(int total,List<?> rows){
		this.total = total;
		this.rows = rows;
	}
	
	public PageDataModel(int total,List<?> rows,Integer code,String msg){
		this.total = total;
		this.rows = rows;
		this.code = code;
		this.msg = msg;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	

	
}
