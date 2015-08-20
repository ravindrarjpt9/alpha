package com.skt.web.alpha.service;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.common.exception.ApplicationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class XmppHttpServiceImpl implements XmppHttpService {

	@Override
	public ClientResponse getPostResponse(String path, String jsonInput)
			throws ApplicationException {
		// Instantiating the jersey client
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

		return clientResponse;
	}

	@Override
	public ClientResponse getPostResponse(String path)
			throws ApplicationException {
		// Instantiating the jersey client
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(XmppConstants.XMPP_REST_API_BASE_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(XmppConstants.AUTHORIZATION, XmppConstants.SECRET_KEY)
				.post(ClientResponse.class);

		return clientResponse;
	}

	@Override
	public ClientResponse getPostResponseWithQueryParam(String path,
			String queryParamKey, String queryParamValue, String jsonInput)
			throws ApplicationException {
		// Instantiating the jersey client
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(XmppConstants.XMPP_REST_API_BASE_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.queryParam(queryParamKey, queryParamValue)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(XmppConstants.AUTHORIZATION, XmppConstants.SECRET_KEY)
				.post(ClientResponse.class, jsonInput);

		return clientResponse;
	}

	@Override
	public ClientResponse getPostResponseWithQueryParam(String path,
			String queryParamKey, String queryParamValue)
			throws ApplicationException {
		// Instantiating the jersey client
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(XmppConstants.XMPP_REST_API_BASE_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.queryParam(queryParamKey, queryParamValue)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(XmppConstants.AUTHORIZATION, XmppConstants.SECRET_KEY)
				.post(ClientResponse.class);

		return clientResponse;
	}

	@Override
	public ClientResponse getDeleteResponse(String path)
			throws ApplicationException {
		// Instantiating the jersey client
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(XmppConstants.XMPP_REST_API_BASE_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(XmppConstants.AUTHORIZATION, XmppConstants.SECRET_KEY)
				.delete(ClientResponse.class);

		return clientResponse;
	}

	@Override
	public ClientResponse getDeleteResponseWithQueryParam(String path,
			String queryParamKey, String queryParamValue)
			throws ApplicationException {
		// Instantiating the jersey client
		Client client = Client.create();

		// Creating a webResource with the Base URL
		WebResource webResource = client
				.resource(XmppConstants.XMPP_REST_API_BASE_URL);

		// Fetching response after sending request on the specified path
		ClientResponse clientResponse = webResource.path(path)
				.queryParam(queryParamKey, queryParamValue)
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header(XmppConstants.AUTHORIZATION, XmppConstants.SECRET_KEY)
				.delete(ClientResponse.class);

		return clientResponse;
	}
}
