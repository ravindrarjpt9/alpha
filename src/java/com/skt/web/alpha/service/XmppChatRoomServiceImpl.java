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
public class XmppChatRoomServiceImpl implements XmppChatRoomService {

	@Autowired
	XmppHttpService xmppHttpService;

	@Autowired
	Gson gson;

	@Override
	public void createChatRoom(XmppChatRoomTo xmppChatRoomTo)
			throws ApplicationException {
		// Converting xmppChatRoomTo to JSON string
		String jsonInput = gson.toJson(xmppChatRoomTo);

		// Setting the request path
		String path = XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL;

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

	@Override
	public void addMemberToChatRoom(String roomName, String userName)
			throws ApplicationException {
		addUserWithRoleToChatRoom(roomName, userName, XmppChatRoomRole.members);
	}

	@Override
	public void addOwnerToChatRoom(String roomName, String userName)
			throws ApplicationException {
		addUserWithRoleToChatRoom(roomName, userName, XmppChatRoomRole.owners);
	}

	@Override
	public void addUserWithRoleToChatRoom(String roomName, String userName,
			XmppChatRoomRole xmppChatRoomRole) throws ApplicationException {

		// Setting the request path
		StringBuilder path = new StringBuilder();
		// Appending CHAT_ROOMS_URL
		path.append(XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL);
		// Appending roomName
		path.append(roomName);
		path.append(XmppConstants.SLASH);
		// Appending role
		path.append(xmppChatRoomRole);
		path.append(XmppConstants.SLASH);
		// Appending userName
		path.append(userName);
		path.append(XmppConstants.SLASH);

		// Receiving clientResponse from post
		ClientResponse clientResponse = xmppHttpService.getPostResponse(path
				.toString());

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_201) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}
	}

	@Override
	public void deleteMemberFromChatRoom(String roomName, String userName)
			throws ApplicationException {
		deleteUserWithRoleFromChatRoom(roomName, userName,
				XmppChatRoomRole.members);
	}

	@Override
	public void deleteOwnerFromChatRoom(String roomName, String userName)
			throws ApplicationException {
		deleteUserWithRoleFromChatRoom(roomName, userName,
				XmppChatRoomRole.owners);
	}

	@Override
	public void deleteUserWithRoleFromChatRoom(String roomName,
			String userName, XmppChatRoomRole xmppChatRoomRole)
			throws ApplicationException {
		// Setting the request path
		StringBuilder path = new StringBuilder();
		// Appending CHAT_ROOMS_URL
		path.append(XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL);
		// Appending roomName
		path.append(roomName);
		path.append(XmppConstants.SLASH);
		// Appending role
		path.append(xmppChatRoomRole);
		path.append(XmppConstants.SLASH);
		// Appending userName
		path.append(userName);
		path.append(XmppConstants.SLASH);

		// Receiving clientResponse from delete request
		ClientResponse clientResponse = xmppHttpService.getDeleteResponse(path
				.toString());

		// Throwing ApplicationException if success response was not received
		if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_200) {
			throw new ApplicationException(
					XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
							+ clientResponse.getStatus());
		}
	}
}
