package com.skt.web.alpha.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.skt.web.common.model.BaseModel;

@Entity
@Table(name = "user_push_message")
public class UserPushMessage extends BaseModel  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8451327261597998589L;

    @Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

    @Column(name = "U_ID")
	private int uId;
    
	@Lob
	@Column(name = "MESSAGE")
	private String message;

	@Column(name = "MESSAGE__CREATION_TIME", updatable = false)
	private Date messageCreationTime = new Date();

	@Lob
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "PUSH_ID")
	private String pushId;

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
		return messageCreationTime;
	}

	public void setMessageCreationTime(Date messageCreationTime) {
		this.messageCreationTime = messageCreationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}
	
	
}
