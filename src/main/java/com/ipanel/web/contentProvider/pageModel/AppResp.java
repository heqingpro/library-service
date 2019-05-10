package com.ipanel.web.contentProvider.pageModel;


public class AppResp  {
	
	private Integer id; //内容提供商id
	
	private String name ;//专区内容提供商名

	private String enName ;//英文名称
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
}
