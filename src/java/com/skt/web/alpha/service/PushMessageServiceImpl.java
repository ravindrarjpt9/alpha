package com.skt.web.alpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.PushMessageDao;
import com.skt.web.alpha.model.PushMessage;
import com.skt.web.common.exception.ApplicationException;

@Service
public class PushMessageServiceImpl implements PushMessageService {

	@Autowired
	PushMessageDao pushMessageDao;

	@Override
	public PushMessage createPushMessage(PushMessage pushMessage)
			throws ApplicationException {
		// Creating the pushMessage in DB
		return pushMessageDao.persist(pushMessage);
	}

	@Override
	public PushMessage updatePushMessage(PushMessage pushMessage)
			throws ApplicationException {
		// Updating the pushMessage in DB
		return pushMessageDao.merge(pushMessage);
	}
}
