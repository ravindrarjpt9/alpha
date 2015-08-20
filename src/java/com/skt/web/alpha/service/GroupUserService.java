package com.skt.web.alpha.service;

import java.util.List;

import com.skt.web.alpha.model.GroupUser;
import com.skt.web.common.exception.ApplicationException;

public interface GroupUserService {

	GroupUser createGroupUser(GroupUser groupUser) throws ApplicationException;

	GroupUser updateGroupUser(GroupUser groupUser) throws ApplicationException;

	GroupUser getGroupUser(int groupUserId) throws ApplicationException;

	List<Object[]> findAllGroupUserTosByUserId(int userId)
			throws ApplicationException;

	List<Object[]> findAllUserNameTosByGroupId(int groupId)
			throws ApplicationException;
	
	List<Object[]> findAllUserIdTosByGroupId(int groupId)
			throws ApplicationException;

	List<Integer> findAllGroupIdsByUserId(int userId)
			throws ApplicationException;

	List<Object[]> findAllGroupDisplayNameTosByUserId(int userId)
			throws ApplicationException;

	GroupUser findByUserIdAndGroupId(int userId, int groupId)
			throws ApplicationException;
}
