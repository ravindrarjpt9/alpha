package com.skt.web.alpha.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.constants.UserGroupRole;
import com.skt.web.alpha.constants.UserGroupStatus;
import com.skt.web.alpha.model.Group;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.GroupUserService;
import com.skt.web.alpha.service.UserService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.GroupDisplayNameTo;
import com.skt.web.alpha.to.GroupUserTo;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.UserNameTo;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class GroupUserController {
	private static final Logger LOG = Logger
			.getLogger(GroupUserController.class);

	@Autowired
	GroupUserService groupUserService;
	
	@Autowired
	UserService UserService;

	@Transactional
	@RequestMapping(value = "/getGroupsByUserId/{userId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getGroupUserTosByUserId(@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;

		try {
			User user = UserService.getUser(userId);
			if(user != null){
			// Fetching the required data from DB as a List of Object[]
			List<Object[]> groupUserToObjectArrays = groupUserService
					.findAllGroupUserTosByUserId(userId);

			// Creating instance of groupUserTos to be returned
			List<GroupUserTo> groupUserTos = new ArrayList<>();

			// Populating groupUserTos from groupUserToObjectArrays
			for (Object[] groupUserToObjectArray : groupUserToObjectArrays) {

				// Instantiation GroupUserTo for each iteration
				GroupUserTo groupUserTo = new GroupUserTo();

				// Initializing the GroupUserTo
				groupUserTo.setId((int) groupUserToObjectArray[0]);
				groupUserTo.setGroup((Group) groupUserToObjectArray[1]);
				groupUserTo
						.setUserGroupStatus((UserGroupStatus) groupUserToObjectArray[2]);
				groupUserTo
						.setUserGroupRole((UserGroupRole) groupUserToObjectArray[3]);
				groupUserTo.setUserAddTime((Date) groupUserToObjectArray[4]);
				//User Status for all groups
				groupUserTo.setStatus(user.getUserStatus().toString());

				// Adding the instance of groupUserTo to groupUserTos
				groupUserTos.add(groupUserTo);
			}

			// Returning the List of GroupUserTos
			data = groupUserTos;
			success = true;
			}else
			{
				LOG.warn("User does not exit with this user id ["+userId+"]");
				data = new ErrorResponse(404, "User does not exit with this user id ["+userId+"]");
			}
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getUserNameTosByGroupId/{groupId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getUserNameTosByGroupId(@PathVariable("groupId") int groupId) {
		boolean success = false;
		Object data = null;

		try {
			// Fetching the required data from DB as a List of Object[]
			List<Object[]> userNameToObjectArrays = groupUserService
					.findAllUserNameTosByGroupId(groupId);

			// Creating instance of userNameTos to be returned
			List<UserNameTo> userNameTos = new ArrayList<>();

			// Populating userNameTos from userNameToObjectArrays
			for (Object[] userNameToObjectArray : userNameToObjectArrays) {

				// Instantiation UserNameTo for each iteration
				UserNameTo userNameTo = new UserNameTo();

				// Initializing the UserNameTo
				userNameTo.setId((int) userNameToObjectArray[0]);
				userNameTo.setFullName((String) userNameToObjectArray[1]);

				// Adding the instance of userNameTo to userNameTos
				userNameTos.add(userNameTo);
			}

			// Returning the List of userNameTos
			data = userNameTos;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	// TODO: Delete this controller if not needed
	@Transactional
	@RequestMapping(value = "/getGroupIdsByUserId/{userId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getGroupIdsByUserId(@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;

		try {
			// Fetching the required data from DB as a List of Integer
			List<Integer> groupIds = groupUserService
					.findAllGroupIdsByUserId(userId);

			// Returning the List of groupIds
			data = groupIds;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getGroupDisplayNameTosByUserId/{userId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getGroupDisplayNameTosByUserId(
			@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;

		try {
			// Fetching the required data from DB as a List of Object[]
			List<Object[]> groupDisplayNameToObjectArrays = groupUserService
					.findAllGroupDisplayNameTosByUserId(userId);

			// Creating instance of groupDisplayNameTos to be returned
			List<GroupDisplayNameTo> groupDisplayNameTos = new ArrayList<>();

			// Populating groupDisplayNameTos from
			// groupDisplayNameToObjectArrays
			for (Object[] groupDisplayNameToObjectArray : groupDisplayNameToObjectArrays) {

				// Instantiation GroupDisplayNameTo for each iteration
				GroupDisplayNameTo groupDisplayNameTo = new GroupDisplayNameTo();

				// Initializing the groupDisplayNameTo
				groupDisplayNameTo
						.setId((int) groupDisplayNameToObjectArray[0]);
				groupDisplayNameTo
						.setDisplayName((String) groupDisplayNameToObjectArray[1]);

				// Adding the instance of groupDisplayNameTo to
				// groupDisplayNameTos
				groupDisplayNameTos.add(groupDisplayNameTo);
			}

			// Returning the List of groupDisplayNameTos
			data = groupDisplayNameTos;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}
}
