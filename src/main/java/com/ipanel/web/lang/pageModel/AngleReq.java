package com.ipanel.web.lang.pageModel;

/**
 * @author fangg
 * 2017年5月31日 下午5:32:33
 */
public class AngleReq {
	
	private Integer page=1;
	
	private Integer rows=10;
	
	private Integer id;
	
	private String name;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AngleReq [page=" + page + ", rows=" + rows + ", id=" + id
				+ ", name=" + name + "]";
	}

	
	
}
