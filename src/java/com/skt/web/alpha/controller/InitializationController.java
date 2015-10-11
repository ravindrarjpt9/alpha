package com.skt.web.alpha.controller;

import org.apache.log4j.Logger;

import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.XmppConstants;



public class InitializationController {

	private static final Logger LOG = Logger.getLogger(InitializationController.class);
    String host;
	String minimumFriendsRequired; 
	String xmppServerIpAddress;
	String androidAppCurrentVersion;
	String iosAppCurrentVersion;
	String androidFourceUpdate;
	String iosFourceUpdate;
	String xmppClientPort;
	String xmppServerPortUnsecure;
	String adminUserFbId ;
	 
    
	public void initIt() throws Exception {
		Constants.HOST = host;
		Constants.MINIMUM_FRIENDS_REQUIRED = Integer.parseInt(minimumFriendsRequired);
		XmppConstants.XMPP_SERVER_IP_ADDRESS = xmppServerIpAddress;
		XmppConstants.ANDROID_APP_CURRENT_VERSION = androidAppCurrentVersion;
		XmppConstants.IOS_APP_CURRENT_VERSION = iosAppCurrentVersion;
		XmppConstants.ANRDOID_FOURCE_UPDATE = Boolean.parseBoolean(androidFourceUpdate);
		XmppConstants.IOS_FOURCE_UPDATE = Boolean.parseBoolean(iosFourceUpdate);
		XmppConstants.XMPP_CLIENT_PORT = xmppClientPort;
		XmppConstants.XMPP_SERVER_PORT_UNSECURE = xmppServerPortUnsecure;
		Constants.ADMIN_USER_FB_ID = adminUserFbId;

		LOG.info("Init method after properties are set : " + host + " -- "+minimumFriendsRequired);
	}
	
	public  String getHost() {
		return host;
	}

	public  void setHost(String host) {
		this.host = host;
	}

	public String getMinimumFriendsRequired() {
		return minimumFriendsRequired;
	}

	public void setMinimumFriendsRequired(String minimumFriendsRequired) {
		this.minimumFriendsRequired = minimumFriendsRequired;
	}

	public void cleanUp() throws Exception {
				
	  System.out.println("Spring Container is destroy! Customer clean up");
	}

	public String getXmppServerIpAddress() {
		return xmppServerIpAddress;
	}

	public void setXmppServerIpAddress(String xmppServerIpAddress) {
		this.xmppServerIpAddress = xmppServerIpAddress;
	}

	public String getAndroidAppCurrentVersion() {
		return androidAppCurrentVersion;
	}

	public void setAndroidAppCurrentVersion(String androidAppCurrentVersion) {
		this.androidAppCurrentVersion = androidAppCurrentVersion;
	}

	public String getIosAppCurrentVersion() {
		return iosAppCurrentVersion;
	}

	public void setIosAppCurrentVersion(String iosAppCurrentVersion) {
		this.iosAppCurrentVersion = iosAppCurrentVersion;
	}

	public String getAndroidFourceUpdate() {
		return androidFourceUpdate;
	}

	public void setAndroidFourceUpdate(String androidFourceUpdate) {
		this.androidFourceUpdate = androidFourceUpdate;
	}

	public String getIosFourceUpdate() {
		return iosFourceUpdate;
	}

	public void setIosFourceUpdate(String iosFourceUpdate) {
		this.iosFourceUpdate = iosFourceUpdate;
	}

	public String getXmppClientPort() {
		return xmppClientPort;
	}

	public void setXmppClientPort(String xmppClientPort) {
		this.xmppClientPort = xmppClientPort;
	}

	public String getXmppServerPortUnsecure() {
		return xmppServerPortUnsecure;
	}

	public void setXmppServerPortUnsecure(String xmppServerPortUnsecure) {
		this.xmppServerPortUnsecure = xmppServerPortUnsecure;
	}

	public String getAdminUserFbId() {
		return adminUserFbId;
	}

	public void setAdminUserFbId(String adminUserFbId) {
		this.adminUserFbId = adminUserFbId;
	}
	
		
}
