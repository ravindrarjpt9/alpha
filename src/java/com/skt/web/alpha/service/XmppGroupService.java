package com.skt.web.alpha.service;

import com.skt.web.alpha.to.XmppGroupTo;
import com.skt.web.common.exception.ApplicationException;

public interface XmppGroupService {

	void createGroup(XmppGroupTo xmppGroupTo) throws ApplicationException;
}
