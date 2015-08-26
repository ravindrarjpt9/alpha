package com.skt.web.alpha.to;

import java.util.Date;

public class UserPushMessageTo {

	private int id;
    private String pushId;
    private String message;
    private String status;
    private Date messageCreationTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPushId() {
		return pushId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getMessageCreationTime() {
		return messageCreationTime;
	}
	public void setMessageCreationTime(Date messageCreationTime) {
		this.messageCreationTime = messageCreationTime;
	}
    
    
}
