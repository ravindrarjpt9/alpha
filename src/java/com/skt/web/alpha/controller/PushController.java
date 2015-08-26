package com.skt.web.alpha.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.service.PushRegistrationService;
import com.skt.web.alpha.to.Response;

@Controller
public class PushController {
	/**
	 * Log the error messages.
	 */
	private static final Logger LOG = Logger.getLogger(PushController.class);
 // TODO: Not in use.
	@Autowired
	private PushRegistrationService pushRegistrationService;

	@Transactional
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	@ResponseBody
	public Response sendMessage(@RequestBody String message) {
		LOG.info("Push Message: " + message);

		boolean success = false;
		Object data = null;

		try {
			List<String> allDevices = pushRegistrationService
					.getAllRegisteredDevice();
			pushRegistrationService.send(allDevices, message);
			data = "Message Pushed successfully.";
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			data = e.getMessage();
		}
		return new Response(success, data);
	}
	
	
}
