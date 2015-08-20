package com.skt.web.alpha.service;

import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.to.XmppChatRoomTo;
import com.skt.web.common.exception.ApplicationException;

public interface XmppTopicService {
	void createTopicChat(XmppChatRoomTo xmppChatRoomTo)
			throws ApplicationException;

	void addMemberToTopicChat(String roomName, String userName)
			throws ApplicationException;

	void addOwnerToTopicChat(String roomName, String userName)
			throws ApplicationException;

	void addUserWithRoleToTopicChat(String roomName, String userName,
			XmppChatRoomRole xmppChatRoomRole) throws ApplicationException;

	void deleteMemberFromTopicChat(String roomName, String userName)
			throws ApplicationException;

	void deleteOwnerFromTopicChat(String roomName, String userName)
			throws ApplicationException;

	void deleteUserWithRoleFromTopicChat(String roomName, String userName,
			XmppChatRoomRole xmppChatRoomRole) throws ApplicationException;
}
