package com.skt.web.alpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.alpha.to.XmppChatRoomTo;
import com.skt.web.common.exception.ApplicationException;
import com.sun.jersey.api.client.ClientResponse;

@Service
public class XmppTopicServiceImpl implements XmppTopicService {

	@Autowired
	XmppHttpService xmppHttpService;

	@Autowired
	Gson gson;

	@Override
	public void createTopicChat(XmppChatRoomTo xmppChatRoomTo)
			throws ApplicationException {
		// Converting xmppChatRoomTo to JSON string
		String jsonInput = gson.toJson(xmppChatRoomTo);

		// Setting the request path
		String path = XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL;

		// Setting Query Params
		String queryParamKey = XmppConstants.SERVICE_NAME;
		String queryParamValue = XmppConstants.TOPICS_SERVICE_NAME;

		// Receiving clientResponse from post
		ClientResponse clientResponse = xmppHttpService
				.getPostResponseWithQueryParam(path, queryParamKey,
						queryParamValue, jsonInput);

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_201) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}
	}

	@Override
	public void addMemberToTopicChat(String topicChatName, String userName)
			throws ApplicationException {
		addUserWithRoleToTopicChat(topicChatName, userName,
				XmppChatRoomRole.members);
	}

	@Override
	public void addOwnerToTopicChat(String topicChatName, String userName)
			throws ApplicationException {
		addUserWithRoleToTopicChat(topicChatName, userName,
				XmppChatRoomRole.owners);
	}

	@Override
	public void addUserWithRoleToTopicChat(String topicChatName,
			String userName, XmppChatRoomRole xmppChatRoomRole)
			throws ApplicationException {

		// Setting the request path
		StringBuilder path = new StringBuilder();
		// Appending CHAT_ROOMS_URL
		path.append(XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL);
		// Appending topicChatName
		path.append(topicChatName);
		path.append(XmppConstants.SLASH);
		// Appending role
		path.append(xmppChatRoomRole);
		path.append(XmppConstants.SLASH);
		// Appending userName
		path.append(userName);
		path.append(XmppConstants.SLASH);

		// Setting Query Params
		String queryParamKey = XmppConstants.SERVICE_NAME;
		String queryParamValue = XmppConstants.TOPICS_SERVICE_NAME;

		// Receiving clientResponse from post
		ClientResponse clientResponse = xmppHttpService
				.getPostResponseWithQueryParam(path.toString(), queryParamKey,
						queryParamValue);

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_201) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}

	}

	@Override
	public void deleteMemberFromTopicChat(String topicChatName, String userName)
			throws ApplicationException {
		deleteUserWithRoleFromTopicChat(topicChatName, userName,
				XmppChatRoomRole.members);
	}

	@Override
	public void deleteOwnerFromTopicChat(String topicChatName, String userName)
			throws ApplicationException {
		deleteUserWithRoleFromTopicChat(topicChatName, userName,
				XmppChatRoomRole.owners);
	}

	@Override
	public void deleteUserWithRoleFromTopicChat(String topicChatName,
			String userName, XmppChatRoomRole xmppChatRoomRole)
			throws ApplicationException {
		// Setting the request path
		StringBuilder path = new StringBuilder();
		// Appending CHAT_ROOMS_URL
		path.append(XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL);
		// Appending roomName
		path.append(topicChatName);
		path.append(XmppConstants.SLASH);
		// Appending role
		path.append(xmppChatRoomRole);
		path.append(XmppConstants.SLASH);
		// Appending userName
		path.append(userName);
		path.append(XmppConstants.SLASH);

		// Setting Query Params
		String queryParamKey = XmppConstants.SERVICE_NAME;
		String queryParamValue = XmppConstants.TOPICS_SERVICE_NAME;

		// Receiving clientResponse from delete request
		ClientResponse clientResponse = xmppHttpService
				.getDeleteResponseWithQueryParam(path.toString(),
						queryParamKey, queryParamValue);

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_200) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}
	}
}
