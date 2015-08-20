package com.skt.web.alpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.alpha.to.XmppGroupTo;
import com.skt.web.common.exception.ApplicationException;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class XmppGroupServiceImpl implements XmppGroupService {

	@Autowired
	XmppHttpService xmppHttpService;

	@Autowired
	Gson gson;

	@Override
	public void createGroup(XmppGroupTo xmppGroupTo)
			throws ApplicationException {
		// Converting xmppGroupTo to JSON string
		String jsonInput = gson.toJson(xmppGroupTo);

		// Setting the request path
		String path = XmppConstants.XMPP_REST_API_GROUPS_URL;

		// Receiving clientResponse from post
		ClientResponse clientResponse = xmppHttpService.getPostResponse(path,
				jsonInput);

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_201) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}
	}
}
