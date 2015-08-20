package com.skt.web.alpha.service;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.alpha.to.XmppUserTo;
import com.skt.web.common.exception.ApplicationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class XmppUserServiceImpl implements XmppUserService {

	@Autowired
	XmppHttpService xmppHttpService;

	@Autowired
	Gson gson;

	@Override
	public void createUser(XmppUserTo xmppUserTo) throws ApplicationException {
		//Converting xmppUserTo to JSON string
		String jsonInput = gson.toJson(xmppUserTo);
//
//		// Setting the request path
		String path = XmppConstants.XMPP_REST_API_USERS_URL;
//
//		// Receiving clientResponse from post
//		ClientResponse clientResponse = xmppHttpService.getPostResponse(path,
//				jsonInput);
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(XmppConstants.XMPP_REST_API_BASE_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(XmppConstants.AUTHORIZATION, XmppConstants.SECRET_KEY)
				.post(ClientResponse.class, jsonInput);

		

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_201) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}
	}
}
