package com.skt.web.alpha.service;

import com.skt.web.common.exception.ApplicationException;
import com.sun.jersey.api.client.ClientResponse;

public interface XmppHttpService {

	ClientResponse getPostResponse(String path, String jsonInput)
			throws ApplicationException;

	ClientResponse getPostResponse(String path) throws ApplicationException;

	ClientResponse getPostResponseWithQueryParam(String path,
			String queryParamKey, String queryParamValue, String jsonInput)
			throws ApplicationException;

	ClientResponse getPostResponseWithQueryParam(String path,
			String queryParamKey, String queryParamValue)
			throws ApplicationException;

	ClientResponse getDeleteResponse(String path) throws ApplicationException;

	ClientResponse getDeleteResponseWithQueryParam(String path,
			String queryParamKey, String queryParamValue)
			throws ApplicationException;
}
