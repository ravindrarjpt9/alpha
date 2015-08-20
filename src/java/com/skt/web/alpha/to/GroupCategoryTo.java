package com.skt.web.alpha.to;

import java.util.List;

public class GroupCategoryTo {

	private String id;
	private String groupCategory;
	private List<String> topicTypeList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGroupCategory() {
		return groupCategory;
	}
	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}
	public List<String> getTopicTypeList() {
		return topicTypeList;
	}
	public void setTopicTypeList(List<String> topicTypeList) {
		this.topicTypeList = topicTypeList;
	}
	
}
