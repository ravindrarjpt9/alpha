package com.skt.web.alpha.service;

import com.skt.web.alpha.to.XmppUserTo;
import com.skt.web.common.exception.ApplicationException;

public interface XmppUserService {

	void createUser(XmppUserTo xmppUserTo) throws ApplicationException;
}
