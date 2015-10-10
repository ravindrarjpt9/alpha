package com.skt.web.alpha.to;

public class XmppResponseTo {

	private String server;
	private String port;
	private String androidVersion;
	private String iosVersion;
	
	private boolean iosFourceUpdate;
	private boolean androidFourceUpdate;
	
	
	
	public XmppResponseTo(String server, String port, String androidVersion, String iosVersion, boolean iosFourceUpdate, boolean androidFourceUpdate) {
		
		this.server = server;
		this.port = port;
		this.androidVersion = androidVersion;
		this.iosVersion = iosVersion;
		this.iosFourceUpdate = iosFourceUpdate;
		this.androidFourceUpdate = androidFourceUpdate;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	public String getIosVersion() {
		return iosVersion;
	}
	public void setIosVersion(String iosVersion) {
		this.iosVersion = iosVersion;
	}
	public boolean isIosFourceUpdate() {
		return iosFourceUpdate;
	}
	public void setIosFourceUpdate(boolean iosFourceUpdate) {
		this.iosFourceUpdate = iosFourceUpdate;
	}
	public boolean isAndroidFourceUpdate() {
		return androidFourceUpdate;
	}
	public void setAndroidFourceUpdate(boolean androidFourceUpdate) {
		this.androidFourceUpdate = androidFourceUpdate;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	
	
}
