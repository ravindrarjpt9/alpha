package com.skt.web.alpha.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.skt.web.common.model.BaseModel;

@NamedQueries({
		@NamedQuery(name = "findAllRegisteredDevice", query = "from com.skt.web.alpha.model.PushRegistration"),
		@NamedQuery(name = "findRegistrationKey", query = "from com.skt.web.alpha.model.PushRegistration as pushReg "
				+ "where pushReg.registrationKey = ? AND pushReg.userId = ?"),
		@NamedQuery(name = "findAllRegisteredDeviceBy", query = "from com.skt.web.alpha.model.PushRegistration as pushReg "
				+ "where pushReg.userId IN :userIds") })
@Entity
@Table(name = "push_registrations")
public class PushRegistration extends BaseModel {
	private static final long serialVersionUID = 7007945542951383736L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "REGISTRATION_KEY")
	private String registrationKey;

	@Column(name = "USER_ID", nullable = false)
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistrationKey() {
		return registrationKey;
	}

	public void setRegistrationKey(String registrationKey) {
		this.registrationKey = registrationKey;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}