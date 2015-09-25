package com.skt.web.alpha.to;

public class XmppResponseTo {

	private String server;
	private String port;
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
	public XmppResponseTo(String server, String port) {
		super();
		this.server = server;
		this.port = port;
	}
	
	
}
