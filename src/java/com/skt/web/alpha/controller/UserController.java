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
import com.skt.web.alpha.constants.UserStatus;
import com.skt.web.alpha.dao.UserDao;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.PushRegistrationService;
import com.skt.web.alpha.service.UserService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.UserBlockTo;
import com.skt.web.alpha.to.TopicIdTo;
import com.skt.web.alpha.to.UserTo;
import com.skt.web.alpha.to.UsersResponseTo;
import com.skt.web.alpha.util.UserUtils;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserUtils userUtils;
	
	@Autowired
	private PushRegistrationService pushRegistrationService;

	@Transactional
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response updateUser(@RequestBody UserTo userTo) {
		boolean success = false;
		Object data = null;
		try {
			int userId = userTo.getId();

			// Checking if user exists in the DB
			User user = userService.getUser(userId);

			// Setting user from userTo
			userUtils.setUserFromUserTo(user, userTo);

			// Updating the user in DB
			user = userService.updateUser(user);

			// Setting userTo from user, after DB update
			userUtils.setUserToFromUser(userTo, user);

			// Returning userTo
			data = userTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getUser(@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;
		try {
			// Fetching user from DB
			User user = userService.getUser(userId);

			// Creating userTo instance to be sent to client
			UserTo userTo = new UserTo();
			// Setting userTo from user
			userUtils.setUserToFromUser(userTo, user);

			// Returning the userTo instance
			data = userTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	// With Path Params
	// @RequestMapping(value = "/likeUser/{userId}", method =
	// RequestMethod.POST, consumes = "application/json", produces =
	// "application/json")
	// @ResponseBody
	// public Response likeUser(@PathVariable("userId") int userId) {

	// With JSON Request Body
	@Transactional
	@RequestMapping(value = "/likeUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response likeUser(@RequestBody TopicIdTo userIdTo) {
		boolean success = false;
		Object data = null;
		try {
			int userId = userIdTo.getId();
			// Fetching the user from DB
			User user = userService.getUser(userId);

			// Fetching the userLikeCount
			int userLikeCount = user.getUserLikeCount();
			userLikeCount++;

			// Updating the userLikeCount
			user.setUserLikeCount(userLikeCount);

			// Updating the user in DBs
			user = userService.updateUser(user);

			// Creating userTo instance to be sent to client
			UserTo userTo = new UserTo();
			// Setting userTo from user
			userUtils.setUserToFromUser(userTo, user);

			// Returning the userTo instance to client
			data = userTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	// With JSON Request Body
	@Transactional
	@RequestMapping(value = "/spamUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response spamUser(@RequestBody TopicIdTo userIdTo) {
		boolean success = false;
		Object data = null;
		try {
			int userId = userIdTo.getId();
			// Fetching the user from DB
			User user = userService.getUser(userId);

			// Fetching the userSpamCount
			int userSpamCount = user.getUserSpamCount();
			userSpamCount++;

			// Updating the userSpamCount
			user.setUserSpamCount(userSpamCount);

			// Updating the user in DBs
			user = userService.updateUser(user);

			// Creating instance of userTo to be sent to client
			UserTo userTo = new UserTo();
			// Setting userTo from user
			userUtils.setUserToFromUser(userTo, user);

			// Returning the userTo instance
			data = userTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}
	
	@Transactional
	@RequestMapping(value = "/blockUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response deleteUser(@RequestBody UserBlockTo userBlockTo) {
		boolean success = false;
		Object data = null;
		try {
			// Fetching user from DB
			User user = userService.getUser(userBlockTo.getId());
			// Updating user status
			if(userBlockTo.getStatus().equalsIgnoreCase(UserStatus.BLOCKED.toString()))
			{
				user.setUserStatus(UserStatus.BLOCKED);
			}else if(userBlockTo.getStatus().equalsIgnoreCase(UserStatus.DELETED.toString()))
			{
				user.setUserStatus(UserStatus.DELETED);
			}else if(userBlockTo.getStatus().equalsIgnoreCase(UserStatus.PENDING.toString()))
			{
				user.setUserStatus(UserStatus.PENDING);
			}else if(userBlockTo.getStatus().equalsIgnoreCase(UserStatus.ACTIVE.toString()))
			{
				user.setUserStatus(UserStatus.ACTIVE);
			}
			
			//Updating user status
			userService.updateUser(user);
			// Creating userTo instance to be sent to client
			// Sending message to user
			Result status = pushRegistrationService.send(userBlockTo.getPushId(), userBlockTo.getMessage());
			LOG.info("User message status :" + status.toString() + " User Name " + user.getFirstName() + " " + user.getLastName() + " fb Id " + user.getFbUserId());
			
			UserTo userTo = new UserTo();
			// Setting userTo from user
			userUtils.setUserToFromUser(userTo, user);

			// Returning the userTo instance
			data = userTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getAllUsers",method=RequestMethod.GET,produces = "application/json")         
    public @ResponseBody  UsersResponseTo getAll(HttpServletRequest request){
		UsersResponseTo<User> usersResponseTo = null;
		//boolean success = false;
		//Object data = null;
		try
		{
		int rows = Integer.parseInt(request.getParameter("rows"));
        int page = Integer.parseInt(request.getParameter("page"));
        String sidx = request.getParameter("sidx");
        String sord = request.getParameter("sord");
        List<User> list = userService.getUsers(rows, page, sidx, sord);
         usersResponseTo = new UsersResponseTo<>();
        usersResponseTo.setRows(list);
        int count = userService.getNoOfUsers();
        int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
        usersResponseTo.setTotal(total);
        usersResponseTo.setRecords(count);
        usersResponseTo.setPage(page);
       //data = usersResponseTo;
		//success = true;
		}catch(ApplicationException e)
		{
			//data = new ErrorResponse(e.getErrorCode(), e.getMessage());
			LOG.error("Error while getting list of users",e);
		}
		return usersResponseTo;
	}
	
	
	
}
