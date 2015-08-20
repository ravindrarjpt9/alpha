package com.skt.web.alpha.to;

import com.skt.web.alpha.constants.XmppConstants;

public class XmppChatRoomTo {

	private String roomName;

	private String naturalName;

	private String description;

	private String subject;

	// Setting default value as defined in XmppConstants.CHAT_ROOM_MAX_USERS
	private int maxUsers = XmppConstants.CHAT_ROOM_MAX_USERS;

	// Setting default value as true
	private boolean persistent = true;

	// Setting default value as true
	private boolean registrationEnabled = true;

	// Setting default value as true
	private boolean logEnabled = true;

	private Owners owners;

	private Admins admins;

	private Members members;

	private Outcasts outcasts;

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getNaturalName() {
		return naturalName;
	}

	public void setNaturalName(String naturalName) {
		this.naturalName = naturalName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getMaxUsers() {
		return maxUsers;
	}

	public void setMaxUsers(int maxUsers) {
		this.maxUsers = maxUsers;
	}

	public boolean isPersistent() {
		return persistent;
	}

	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}

	public boolean isRegistrationEnabled() {
		return registrationEnabled;
	}

	public void setRegistrationEnabled(boolean registrationEnabled) {
		this.registrationEnabled = registrationEnabled;
	}

	public boolean isLogEnabled() {
		return logEnabled;
	}

	public void setLogEnabled(boolean logEnabled) {
		this.logEnabled = logEnabled;
	}

	public Owners getOwners() {
		return owners;
	}

	public void setOwners(Owners owners) {
		this.owners = owners;
	}

	public Admins getAdmins() {
		return admins;
	}

	public void setAdmins(Admins admins) {
		this.admins = admins;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public Outcasts getOutcasts() {
		return outcasts;
	}

	public void setOutcasts(Outcasts outcasts) {
		this.outcasts = outcasts;
	}
}
