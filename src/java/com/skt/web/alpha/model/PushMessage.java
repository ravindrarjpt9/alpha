package com.skt.web.alpha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skt.web.common.model.BaseModel;

@Entity
@Table(name = "push_messages")
public class PushMessage extends BaseModel {

	private static final long serialVersionUID = 5479827176366673342L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Lob
	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "MESSAGE__CREATION_TIME", updatable = false)
	private Date messageCreationTime = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENDER_USER_ID", foreignKey = @ForeignKey(name = "FK_SENDER_USERS_PUSH_MESSAGES"), nullable = false)
	private User senderUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECEIVER_USER_ID", foreignKey = @ForeignKey(name = "FK_RECEIVER_USERS_PUSH_MESSAGES"), nullable = false)
	private User receiverUser;

	@Column(name = "SENDER_USER_NAME")
	private String senderUserName;

	@Column(name = "SENDER_PUSH_ID")
	private String senderPushId;

	@Column(name = "RECEIVER_PUSH_ID")
	private String receiverPushId;

	@Lob
	@Column(name = "RESULT")
	String result;

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

	// Commenting setter, as messageCreationTime should never be set
	// public void setMessageCreationTime(Date messageCreationTime) {
	// this.messageCreationTime = messageCreationTime;
	// }

	public User getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(User senderUser) {
		this.senderUser = senderUser;
	}

	public User getReceiverUser() {
		return receiverUser;
	}

	public void setReceiverUser(User receiverUser) {
		this.receiverUser = receiverUser;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
