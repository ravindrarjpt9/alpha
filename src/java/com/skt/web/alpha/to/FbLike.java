package com.skt.web.alpha.to;

import java.util.ArrayList;
import java.util.List;

public class FbLike {

	private String id;

	private String category;

	private String createdTime;

	private List<Common> categoryList = new ArrayList<>();

	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public List<Common> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Common> categoryList) {
		this.categoryList = categoryList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
