package com.skt.web.alpha.to;

import java.util.List;

import com.skt.web.alpha.model.*;
public class UsersResponseTo<T> {

	private List<T> rows;
    private Integer total;
    private Integer records;
    private Integer page;
    
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
    
}
