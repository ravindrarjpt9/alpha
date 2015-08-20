package com.skt.web.alpha.to;

import java.util.Date;

import com.google.android.gcm.server.Result;

public class PushMessageTo {

	private int id;

	private String message;

	private Date messageCreationTime;

	private int senderUserId;

	private int receiverUserId;

	private String senderUserName;

	private String senderPushId;

	private String receiverPushId;

	private Result result;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getMessageCreationTime() {
		if (messageCreationTime != null) {
			return (Date) this.messageCreationTime.clone();
		}
		return null;
	}

	public void setMessageCreationTime(Date messageCreationTime) {
		this.messageCreationTime = messageCreationTime;
	}

	public int getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(int senderUserId) {
		this.senderUserId = senderUserId;
	}

	public int getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(int receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public String getSenderPushId() {
		return senderPushId;
	}

	public void setSenderPushId(String senderPushId) {
		this.senderPushId = senderPushId;
	}

	public String getReceiverPushId() {
		return receiverPushId;
	}

	public void setReceiverPushId(String receiverPushId) {
		this.receiverPushId = receiverPushId;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
