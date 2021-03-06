package com.skt.web.alpha.dao;

import java.util.List;

import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.common.dao.BaseDao;

public interface UserPushMessageDao extends BaseDao<UserPushMessage>{

	List<UserPushMessage> getUserPushMessage(int pageSize, int page, String sidx, String sord,int id);

	int getNoOfMessages();
}
