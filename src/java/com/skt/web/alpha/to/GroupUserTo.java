package com.skt.web.alpha.to;

import java.util.Date;

import com.skt.web.alpha.constants.UserGroupRole;
import com.skt.web.alpha.constants.UserGroupStatus;
import com.skt.web.alpha.model.Group;

public class GroupUserTo {

	private int id;

	private Group group;

	// Setting default user status as ACTIVE
	private UserGroupStatus userGroupStatus = UserGroupStatus.ACTIVE;

	// Setting default value of userGroupRole
	private UserGroupRole userGroupRole = UserGroupRole.USER;

	private Date userAddTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public UserGroupStatus getUserGroupStatus() {
		return userGroupStatus;
	}

	public void setUserGroupStatus(UserGroupStatus userGroupStatus) {
		this.userGroupStatus = userGroupStatus;
	}

	public UserGroupRole getUserGroupRole() {
		return userGroupRole;
	}

	public void setUserGroupRole(UserGroupRole userGroupRole) {
		this.userGroupRole = userGroupRole;
	}

	public Date getUserAddTime() {
		return userAddTime;
	}

	public void setUserAddTime(Date userAddTime) {
		this.userAddTime = userAddTime;
	}
}
