package com.skt.web.alpha.dao;

import com.skt.web.alpha.model.Group;
import com.skt.web.common.dao.BaseDao;

public interface GroupDao extends BaseDao<Group> {

	Group findByName(String groupName);

	//Group updateUserCount(int groupId);
}
