package com.skt.web.alpha.controller;

import java.util.Calendar;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CurrentTimeStamp {

	
	@RequestMapping(value = "/getCurrentTimeStamp", method = RequestMethod.GET)
	@ResponseBody
	public long getGroupIdsByUserId() {
		return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
	}
}
