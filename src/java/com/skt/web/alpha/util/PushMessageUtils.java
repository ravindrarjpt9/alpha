package com.skt.web.alpha.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Result;
import com.google.gson.Gson;
import com.skt.web.alpha.model.PushMessage;
import com.skt.web.alpha.to.PushMessageTo;

@Component
public class PushMessageUtils {

	@Autowired
	Gson gson;

	public void setPushMessageFromPushMessageTo(PushMessage pushMessage,
			PushMessageTo pushMessageTo) {
		pushMessage.setId(pushMessageTo.getId());
		pushMessage.setMessage(pushMessageTo.getMessage());
		pushMessage.setReceiverPushId(pushMessageTo.getReceiverPushId());
		pushMessage.setResult(gson.toJson(pushMessageTo.getResult()));
		pushMessage.setSenderPushId(pushMessageTo.getSenderPushId());
		pushMessage.setSenderUserName(pushMessageTo.getSenderUserName());
	}

	public void setPushMessageToFromPushMessage(PushMessageTo pushMessageTo,
			PushMessage pushMessage) {
		pushMessageTo.setId(pushMessage.getId());
		pushMessageTo.setMessage(pushMessage.getMessage());
		pushMessageTo.setMessageCreationTime(pushMessage
				.getMessageCreationTime());
		pushMessageTo.setReceiverPushId(pushMessage.getReceiverPushId());
		pushMessageTo.setReceiverUserId(pushMessage.getReceiverUser().getId());
		pushMessageTo.setResult(gson.fromJson(pushMessage.getResult(),
				Result.class));
		pushMessageTo.setSenderPushId(pushMessage.getSenderPushId());
		pushMessageTo.setSenderUserId(pushMessage.getSenderUser().getId());
		pushMessageTo.setSenderUserName(pushMessage.getSenderUserName());
	}
}
