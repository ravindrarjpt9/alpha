package com.skt.web.alpha.util;

import org.springframework.stereotype.Component;

import com.skt.web.alpha.model.GroupUser;
import com.skt.web.alpha.to.GroupUserTo;

@Component
public class GroupUserUtils {

	public void setGroupUserFromGroupUserTo(GroupUser groupUser,
			GroupUserTo groupUserTo) {
		groupUser.setGroup(groupUserTo.getGroup());
		groupUser.setId(groupUserTo.getId());
		groupUser.setUserGroupRole(groupUserTo.getUserGroupRole());
		groupUser.setUserGroupStatus(groupUserTo.getUserGroupStatus());
	}

	public void setGroupUserToFromGroupUser(GroupUserTo groupUserTo,
			GroupUser groupUser) {
		groupUserTo.setGroup(groupUser.getGroup());
		groupUserTo.setId(groupUser.getId());
		groupUserTo.setUserAddTime(groupUser.getUserAddTime());
		groupUserTo.setUserGroupRole(groupUser.getUserGroupRole());
		groupUserTo.setUserGroupStatus(groupUser.getUserGroupStatus());
	}
}
