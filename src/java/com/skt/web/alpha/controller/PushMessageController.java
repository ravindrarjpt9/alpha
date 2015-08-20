package com.skt.web.alpha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Result;
import com.google.gson.Gson;
import com.skt.web.alpha.model.PushMessage;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.PushMessageService;
import com.skt.web.alpha.service.PushRegistrationService;
import com.skt.web.alpha.service.UserService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.PushMessageTo;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.util.PushMessageUtils;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class PushMessageController {
	private static final Logger LOG = Logger
			.getLogger(PushMessageController.class);

	@Autowired
	PushMessageService pushMessageService;

	@Autowired
	PushRegistrationService pushRegistrationService;

	@Autowired
	UserService userService;

	@Autowired
	PushMessageUtils pushMessageUtils;

	@Autowired
	Gson gson;

	@Transactional
	@RequestMapping(value = "/sendPushMessage", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response sendPushMessage(@RequestBody PushMessageTo pushMessageTo) {
		boolean success = false;
		Object data = null;
		try {
			// Fetching senderUser from DB
			User senderUser = userService.getUser(pushMessageTo
					.getSenderUserId());

			// Fetching receiverUser from DB
			User receiverUser = userService.getUser(pushMessageTo
					.getReceiverUserId());
			String receiverPushId = receiverUser.getPushId();

			// Instantiating PushMessage to be persisted
			PushMessage pushMessage = new PushMessage();

			// Initializing pushMessage
			pushMessageUtils.setPushMessageFromPushMessageTo(pushMessage,
					pushMessageTo);
			pushMessage.setReceiverPushId(receiverPushId);
			pushMessage.setReceiverUser(receiverUser);
			pushMessage.setSenderUser(senderUser);

			// Persisting pushMessage in DB
			pushMessage = pushMessageService.createPushMessage(pushMessage);

			// Updating pushMessageTo from pushMessage
			pushMessageUtils.setPushMessageToFromPushMessage(pushMessageTo,
					pushMessage);

			// Converting pushMessageTo to a JSON string
			String message = gson.toJson(pushMessageTo);

			// Sending the message to receiver
			Result result = pushRegistrationService.send(receiverPushId,
					message);

			// Updating pushMessage with result
			// TODO: Known issue: pushMessage.setResult(gson.toJson(result));

			// Updating pushMessage in DB
			// TODO: Known issue: pushMessage =
			// pushMessageService.updatePushMessage(pushMessage);

			// Updating pushMessageTo to be sent as response
			// TODO: Known issue:
			// pushMessageUtils.setPushMessageToFromPushMessage(pushMessageTo,
			// pushMessage);

			// Sending the pushMessageTo as response to sender
			data = pushMessageTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}
}
