package com.ipanel.web.contentProvider.pageModel;


public class AppReq  {
	
	private int page=1; //第几页
	
	private int rows=10; //每页显示的数量
	
	private Integer id; //内容提供商id
	
	private String name ;//专区内容提供商名
	
	private String enName ;//英文名称
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	
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

	@Override
	public String toString() {
		return "AppReq [page=" + page + ", rows=" + rows + ", id=" + id
				+ ", name=" + name + ", enName=" + enName + "]";
	}

	
	
	
	
	
}
