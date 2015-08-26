package com.skt.web.alpha.util;

import org.springframework.stereotype.Component;

import com.google.android.gcm.server.Result;
import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.alpha.to.UserPushMessageTo;

@Component
public class UserPushMessageUtil {

	public UserPushMessage getUserPushMessageModal(
			UserPushMessage userPushMessage,
			UserPushMessageTo userPushMessageTo, Result result) {
		userPushMessage.setuId(userPushMessageTo.getId());
		userPushMessage.setMessage(userPushMessageTo.getMessage());
		userPushMessage.setPushId(userPushMessageTo.getPushId());
		userPushMessage.setStatus(result.toString());
		return userPushMessage;
	}

	
	
}
