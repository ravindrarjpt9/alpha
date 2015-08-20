package com.skt.web.alpha.service;

import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.to.XmppChatRoomTo;
import com.skt.web.common.exception.ApplicationException;

public interface XmppChatRoomService {

	void createChatRoom(XmppChatRoomTo xmppChatRoomTo)
			throws ApplicationException;

	void addMemberToChatRoom(String roomName, String userName)
			throws ApplicationException;

	void addOwnerToChatRoom(String roomName, String userName)
			throws ApplicationException;

	void addUserWithRoleToChatRoom(String roomName, String userName,
			XmppChatRoomRole xmppChatRoomRole) throws ApplicationException;

	void deleteMemberFromChatRoom(String roomName, String userName)
			throws ApplicationException;

	void deleteOwnerFromChatRoom(String roomName, String userName)
			throws ApplicationException;

	void deleteUserWithRoleFromChatRoom(String roomName, String userName,
			XmppChatRoomRole xmppChatRoomRole) throws ApplicationException;
}
