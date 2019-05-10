package com.ipanel.web.series.pageModel;

public class EntryTypeReq {
	
	private Integer page = 1;
	
	private Integer rows = 10;
	
	private Integer id;
	
	private String name; //分类名称
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EntryTypeReq [page=" + page + ", rows=" + rows + ", id=" + id
				+ ", name=" + name + "]";
	}

	
	
}
