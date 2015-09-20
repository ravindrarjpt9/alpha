package com.skt.web.alpha.to;

import java.util.List;

public class GroupRulesTo {

	private String id;
	private String groupCategory;
	private List<String> rulesList;
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
	
	
	public List<String> getRulesList() {
		return rulesList;
	}
	public void setRulesList(List<String> rulesList) {
		this.rulesList = rulesList;
	}
	
	@Override
	public String toString() {
		
		return "{id :"+this.id+",groupCategory:"+this.groupCategory+",rulesList:"+this.rulesList +"}";
	}
	
}
