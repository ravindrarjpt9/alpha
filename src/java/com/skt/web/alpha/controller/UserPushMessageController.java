package com.skt.web.alpha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.android.gcm.server.Result;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.alpha.service.PushRegistrationService;
import com.skt.web.alpha.service.UserPushMessageService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.UserPushMessageTo;
import com.skt.web.alpha.to.UsersResponseTo;
import com.skt.web.alpha.util.UserPushMessageUtil;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class UserPushMessageController {

	private static final Logger LOG = Logger.getLogger(UserPushMessageController.class);
	
	@Autowired
	private PushRegistrationService pushRegistrationService;

	@Autowired
	UserPushMessageService userPushMessageService;
	
	@Autowired
	UserPushMessageUtil userPushMessageUtil;
	
	@Transactional
	@RequestMapping(value = "/sendUserMessage", method = RequestMethod.POST)
	@ResponseBody
	public Response sendMessage(@RequestBody UserPushMessageTo userPushMessageTo) {
		LOG.info("Push Message: " + userPushMessageTo.getMessage() + " to " + userPushMessageTo.getId());

		boolean success = false;
		Object data = null;

		try {
		
			Result result = pushRegistrationService.send(userPushMessageTo.getPushId(), userPushMessageTo.getMessage());
			LOG.info("User Push Message status ["+result.toString() +"] For Id ["+userPushMessageTo.getPushId()+"]");
			UserPushMessage userPushMessage  = new UserPushMessage();
			userPushMessage = userPushMessageUtil.getUserPushMessageModal(userPushMessage,userPushMessageTo,result);
			userPushMessageService.add(userPushMessage);
			data = "Message Pushed successfully.";
			success = true;
		} catch (Exception e) {
			LOG.error("Error while send user push message ["+e.getMessage()+"]",e);
			data = e.getMessage();
		}
		return new Response(success, data);
	}
	
	@Transactional
	@RequestMapping(value = "/getUserPushMessage/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Response getUserPushMessage(@PathVariable("id") int id,HttpServletRequest request) {
		boolean success = false;
		Object data = null;
		UsersResponseTo<UserPushMessage> usersResponseTo = null;
		try {
			int rows = Integer.parseInt(request.getParameter("rows"));
	        int page = Integer.parseInt(request.getParameter("page"));
	        String sidx = request.getParameter("sidx");
	        String sord = request.getParameter("sord");
	        
			// Fetching the required data from DB as a List of UserPushMessage
			List<UserPushMessage> pushMessagesList = userPushMessageService.getListOfUserPushMessageById(rows,page,sidx,sord,id);
			 usersResponseTo = new UsersResponseTo<>();
		        usersResponseTo.setRows(pushMessagesList);
		        int count = userPushMessageService.getNoOfMessages();
		        int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
		        usersResponseTo.setTotal(total);
		        usersResponseTo.setRecords(count);
		        usersResponseTo.setPage(page);
		        data = usersResponseTo;
				success = true;
			
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
			LOG.error("Error while getting list of User push messages",e);
		}
		return new Response(success, data);
	}
}
