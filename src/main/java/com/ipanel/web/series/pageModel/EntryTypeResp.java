package com.ipanel.web.series.pageModel;

public class EntryTypeResp {
	
	private Integer id;
	
	private String name; //分类名称
	
	private String uniqueImageName;
	
	private String imageUrl;
	
	private String addTime; // 添加时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getUniqueImageName() {
		return uniqueImageName;
	}

	public void setUniqueImageName(String uniqueImageName) {
		this.uniqueImageName = uniqueImageName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
	
	

}
