package com.skt.web.alpha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.UserService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.UserIdTo;
import com.skt.web.alpha.to.UserTo;
import com.skt.web.alpha.util.UserUtils;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserUtils userUtils;

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
	public Response likeUser(@RequestBody UserIdTo userIdTo) {
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
	public Response spamUser(@RequestBody UserIdTo userIdTo) {
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
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response deleteUser(@PathVariable("userId") int userId) {
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

	
	
}
