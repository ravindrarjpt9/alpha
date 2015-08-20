package com.skt.web.alpha.service;

import com.skt.web.alpha.model.PushMessage;
import com.skt.web.common.exception.ApplicationException;

public interface PushMessageService {

	PushMessage createPushMessage(PushMessage pushMessage)
			throws ApplicationException;

	PushMessage updatePushMessage(PushMessage pushMessage)
			throws ApplicationException;
}
