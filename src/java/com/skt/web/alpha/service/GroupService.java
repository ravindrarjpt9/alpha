package com.skt.web.alpha.service;

import com.skt.web.alpha.model.Group;
import com.skt.web.common.exception.ApplicationException;

public interface GroupService {

	Group createGroup(Group group) throws ApplicationException;

	Group updateGroup(Group group) throws ApplicationException;

	Group getGroup(int groupId) throws ApplicationException;

	Group findByName(String groupName) throws ApplicationException;
	
	//Group updateUserCount(int groupId) throws ApplicationException;
}
