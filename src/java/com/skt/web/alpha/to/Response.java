package com.skt.web.alpha.to;

public class Response {

	private boolean success;

	private Object data;

	public Response(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public boolean isSuccess() {
		return success;
	}
}
