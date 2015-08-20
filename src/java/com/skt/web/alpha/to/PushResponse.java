package com.skt.web.alpha.to;

import com.skt.web.common.model.BaseModel;

public class PushResponse {

	private BaseModel model;

	private String from;

	public BaseModel getModel() {
		return model;
	}

	public void setModel(BaseModel model) {
		this.model = model;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
}
