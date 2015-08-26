package com.skt.web.alpha.dao;

import java.util.List;

import com.skt.web.alpha.model.PushRegistration;
import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.common.dao.BaseDao;
import com.skt.web.common.exception.ApplicationException;

public interface PushRegistrationDao extends BaseDao<PushRegistration> {
	List<String> getAllRegisteredDevice() throws ApplicationException;

	boolean findBy(String regKey, int userId) throws ApplicationException;

	List<String> findAllKeysByUserId(int userId) throws ApplicationException;

	List<String> findAllKeysByUserIds(List<Integer> userIds) throws ApplicationException;

	
}
