package com.skt.web.alpha.service;

import java.util.List;

import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.common.exception.ApplicationException;

public interface UserPushMessageService {

	UserPushMessage add(UserPushMessage message) throws ApplicationException;
	
	List<UserPushMessage> getListOfUserPushMessageById(int pageSize, int page, String sidx, String sord,int id);
	int getNoOfMessages() throws ApplicationException;
}
