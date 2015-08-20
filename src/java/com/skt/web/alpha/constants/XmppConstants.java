package com.skt.web.alpha.constants;

public final class XmppConstants {

	private XmppConstants() {
		throw new RuntimeException(
				"Cannot instantiate object of XmppConstants class");
	}

	public static final String HTTP_PROTOCOL_SYNTAX = "http://";

	public static final String HTTPS_PROTOCOL_SYNTAX = "https://";

	public static final String XMPP_SERVER_IP_ADDRESS = "119.9.107.53";

	public static final String COLON = ":";

	public static final String XMPP_SERVER_PORT_UNSECURE = "9090";

	public static final String XMPP_SERVER_PORT_SECURE = "9091";

	public static final String XMPP_REST_API_URL = "/plugins/restapi/v1/";

	// Final URL to be user while creating WebResource
	public static final String XMPP_REST_API_BASE_URL = HTTP_PROTOCOL_SYNTAX
			+ XMPP_SERVER_IP_ADDRESS + COLON + XMPP_SERVER_PORT_UNSECURE
			+ XMPP_REST_API_URL;

	public static final String XMPP_REST_API_GROUPS_URL = "/groups/";

	public static final String XMPP_REST_API_USERS_URL = "/users/";

	public static final String XMPP_REST_API_CHAT_ROOMS_URL = "/chatrooms/";

	public static final String AUTHORIZATION = "Authorization";

	public static final String SECRET_KEY = "G9IiL61zmcD757Dx";

	public static final int HTTP_SUCCESS_RESPONSE_201 = 201;

	public static final int HTTP_SUCCESS_RESPONSE_200 = 200;

	public static final String ERROR_MSG_REST_HTTP_FAILURE = "XMPP REST HTTP Request failed with status: ";

	// Zero means unlimited number of occupants
	public static final int CHAT_ROOM_MAX_USERS = 0;

	public static final String SLASH = "/";

	public static final String SERVICE_NAME = "servicename";

	public static final String TOPICS_SERVICE_NAME = "topics";

	public static final String AT_THE_RATE = "@";

	public static final String NEARGROUP = "neargroup";

	public static final String AT_THE_RATE_NEARGROUP = AT_THE_RATE + NEARGROUP;

	public static final String ERROR_MSG_OWNER_CAN_NOT_LEAVE_TOPIC = "Owner can not leave topic chat";
}
