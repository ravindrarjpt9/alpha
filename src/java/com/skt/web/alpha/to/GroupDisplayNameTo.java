package com.skt.web.alpha.to;

import org.springframework.stereotype.Component;

@Component
public class GroupDisplayNameTo {

	private int id;

	private String displayName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
