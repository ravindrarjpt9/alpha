package com.skt.web.alpha.controller;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class CommanDataController {

	@RequestMapping(value = "/getXmppDetails", method = RequestMethod.GET)
	@ResponseBody
	public Response getXmppDetails() {
		boolean success = false;
		Object data = null;
	}
}
