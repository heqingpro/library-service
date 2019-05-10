package com.ipanel.web.category.pageModel;



public class NodeReq {
	
	private int page=1; //第几页
	
	private int rows=10; //每页显示的数量
	
	private Integer id;
	
	private Integer pId;

	private Integer cpId;
	
	private String name;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getCpId() {
		return cpId;
	}

	public void setCpId(Integer cpId) {
		this.cpId = cpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	@Override
	public String toString() {
		return "NodeReq [page=" + page + ", rows=" + rows + ", id=" + id
				+ ", pId=" + pId + ", cpId=" + cpId + ", name=" + name + "]";
	}

	
	
	
}
