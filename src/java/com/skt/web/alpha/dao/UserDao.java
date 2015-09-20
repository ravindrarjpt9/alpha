package com.skt.web.alpha.dao;

import java.util.List;

import com.skt.web.alpha.model.User;
import com.skt.web.common.dao.BaseDao;

public interface UserDao extends BaseDao<User> {

	User findByFbUserId(String fbUserId);

	List<User> getUsers(int pageSize, int page, String sidx, String sord);
	
	 int getNoOfRecords();
	 
	 void getDeleteUsers(int userId);

}
