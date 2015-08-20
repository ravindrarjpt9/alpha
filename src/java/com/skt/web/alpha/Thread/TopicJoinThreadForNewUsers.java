package com.skt.web.alpha.Thread;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.common.exception.ApplicationException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TopicJoinThreadForNewUsers implements Runnable{

private static final Logger LOG = Logger.getLogger(TopicJoinThreadForNewUsers.class);
	
	private Integer userId;
	private XmppChatRoomRole xmppChatRoomRole;
	
	private List<Integer> topicList;
	
	
	
	
	public TopicJoinThreadForNewUsers(Integer userId, XmppChatRoomRole xmppChatRoomRole,
			List<Integer> topicList) {
		
		this.userId = userId;
		this.xmppChatRoomRole = xmppChatRoomRole;
		
		this.topicList = Collections.synchronizedList(topicList);
	}
	
	public XmppChatRoomRole getXmppChatRoomRole() {
		return xmppChatRoomRole;
	}
	public void setXmppChatRoomRole(XmppChatRoomRole xmppChatRoomRole) {
		this.xmppChatRoomRole = xmppChatRoomRole;
	}
	
	
	@Override
	public void run() {
		LOG.info("Start New User Joining topic chat User Id["+userId+"]");
		// Setting the request path
		for(Integer i :topicList)
		{
			LOG.info("Joing Topic Start ["+topicList+"] user ["+userId+"]" );
				StringBuilder path = new StringBuilder();
				// Appending CHAT_ROOMS_URL
				path.append(XmppConstants.XMPP_REST_API_CHAT_ROOMS_URL);
				// Appending topicChatName
				path.append(String.valueOf(i));
				path.append(XmppConstants.SLASH);
				// Appending role
				path.append(xmppChatRoomRole);
				path.append(XmppConstants.SLASH);
				// Appending userName
				path.append(String.valueOf(userId)+XmppConstants.AT_THE_RATE_NEARGROUP);
				path.append(XmppConstants.SLASH);

				// Setting Query Params
				String queryParamKey = XmppConstants.SERVICE_NAME;
				String queryParamValue = XmppConstants.TOPICS_SERVICE_NAME;

				// Receiving clientResponse from post
				ClientResponse clientResponse = null;
				try {
					clientResponse = getPostResponseWithQueryParam(path.toString(), queryParamKey,
							queryParamValue);
				LOG.info(" Topic join user status ["+clientResponse.getStatus()+"]");
				// Throwing ApplicationException if success response was not received
//				if (clientResponse.getStatus() != XmppConstants.HTTP_SUCCESS_RESPONSE_201) {
//					try {
//						throw new ApplicationException(
//								XmppConstants.ERROR_MSG_REST_HTTP_FAILURE
//										+ clientResponse.getStatus());
					} catch (ApplicationException e) {
						LOG.error("Error while joing topic ["+i+"]" +" user ["+userId+"]",e);
					}
				}

		}
		
	
	public static ClientResponse getPostResponseWithQueryParam(String path,
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

	
}
