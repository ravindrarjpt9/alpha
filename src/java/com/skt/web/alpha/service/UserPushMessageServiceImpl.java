package com.skt.web.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.UserPushMessageDao;
import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.common.exception.ApplicationException;

@Service
public class UserPushMessageServiceImpl implements UserPushMessageService {

	
	@Autowired
	UserPushMessageDao userPushMessageDao;
	@Override
	public UserPushMessage add(UserPushMessage message)
			throws ApplicationException {
		
		return userPushMessageDao.persist(message);
	}
	
	@Override
	public List<UserPushMessage> getListOfUserPushMessageById(int pageSize, int page, String sidx, String sord,int id) {
		
		return userPushMessageDao.getUserPushMessage(pageSize,page,sidx,sord,id);
	}
	
	@Override
	public int getNoOfMessages() throws ApplicationException {
		
		return userPushMessageDao.getNoOfMessages();
	}
}
