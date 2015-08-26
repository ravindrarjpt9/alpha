package com.skt.web.alpha.service;

import java.util.List;

import com.google.android.gcm.server.Result;
import com.skt.web.alpha.model.PushRegistration;
import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.common.exception.ApplicationException;

public interface PushRegistrationService {
	void createPushRegistration(PushRegistration push)
			throws ApplicationException;

	List<String> getAllRegisteredDevice() throws ApplicationException;

	void send(List<String> deviceId, String message)
			throws ApplicationException;

	boolean findByKey(String regKey, int userId) throws ApplicationException;

	List<String> findAllKeysByUserId(int userId) throws ApplicationException;

	List<String> findAllKeysByUserIds(List<Integer> userIds)
			throws ApplicationException;

	Result send(String receiverPushId, String message)
			throws ApplicationException;
	
}
