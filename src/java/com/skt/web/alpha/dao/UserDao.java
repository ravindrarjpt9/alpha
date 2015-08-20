package com.skt.web.alpha.dao;

import com.skt.web.alpha.model.User;
import com.skt.web.common.dao.BaseDao;

public interface UserDao extends BaseDao<User> {

	User findByFbUserId(String fbUserId);

}
