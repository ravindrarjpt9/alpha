package com.skt.web.alpha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.XmppResponseTo;
@Controller
public class CommanDataController {

	@RequestMapping(value = "/getXmppDetails", method = RequestMethod.GET)
	@ResponseBody
	public Response getXmppDetails() {
		boolean success = false;
		Object data = null;
		
		XmppResponseTo xmppResponseTo = new XmppResponseTo(XmppConstants.XMPP_SERVER_IP_ADDRESS, XmppConstants.XMPP_CLIENT_PORT);
		return new Response(true, xmppResponseTo);
	}
}
