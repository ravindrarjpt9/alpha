package com.skt.web.alpha.dao;

import java.util.List;

import com.skt.web.alpha.model.GroupUser;
import com.skt.web.common.dao.BaseDao;

public interface GroupUserDao extends BaseDao<GroupUser> {

	List<Object[]> findAllGroupUserTosByUserId(int userId);

	List<Object[]> findAllUserNameTosByGroupId(int groupId);
	
	List<Object[]> findAllUserIdTosByGroupId(int groupId);

	List<Integer> findAllGroupIdsByUserId(int userId);

	List<Object[]> findAllGroupDisplayNameTosByUserId(int userId);

	GroupUser findByUserIdAndGroupId(int userId, int groupId);
}
