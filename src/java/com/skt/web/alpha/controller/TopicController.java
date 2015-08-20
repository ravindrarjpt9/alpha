package com.skt.web.alpha.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.skt.web.alpha.Thread.TopicJoinThread;
import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.TopicStatus;
import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.constants.XmppConstants;
import com.skt.web.alpha.model.Group;
import com.skt.web.alpha.model.Topic;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.GroupService;
import com.skt.web.alpha.service.GroupUserService;
import com.skt.web.alpha.service.TopicService;
import com.skt.web.alpha.service.UserService;
import com.skt.web.alpha.service.XmppTopicService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.GroupCategoryTo;
import com.skt.web.alpha.to.Owners;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.TopicTo;
import com.skt.web.alpha.to.UserIdTo;
import com.skt.web.alpha.to.XmppChatRoomTo;
import com.skt.web.alpha.util.TopicUtils;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class TopicController {
	private static final Logger LOG = Logger.getLogger(TopicController.class);

	@Autowired
	TopicService topicService;

	@Autowired
	UserService userService;

	@Autowired
	GroupService groupService;

	@Autowired
	GroupUserService groupUserService;

	@Autowired
	XmppTopicService xmppTopicService;

	@Autowired
	TopicUtils topicUtils;

	@Transactional
	@RequestMapping(value = "/createTopic", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response createTopic(@RequestBody TopicTo topicTo) {
		boolean success = false;
		Object data = null;
		try {
			// Gets user from DB if user with ownerUserId exists, otherwise
			// throws an exception if ownerUser not found.
			int ownerUserId = topicTo.getOwnerUserId();
			User ownerUser = userService.getUser(ownerUserId);

			// Gets group from DB if group with groupId exists, otherwise
			// throws an exception if group not found.
			int groupId = topicTo.getGroupId();
			Group group = groupService.getGroup(groupId);

			//Get list of users under group by id.
			List<Object[]> userIdToObjectArrays = groupUserService
					.findAllUserIdTosByGroupId(groupId);
			
			// Checking if the combination of ownerUserId and groupId is valid
			groupUserService.findByUserIdAndGroupId(ownerUserId, groupId);

			// Instantiating topic
			Topic topic = new Topic();

			// Initializing topic
			// Populating topic from topicTo
			topicUtils.setTopicFromTopicTo(topic, topicTo);
			// Setting ownerUser and ownerUserName
			topic.setOwnerUser(ownerUser);
			topic.setOwnerUserName(ownerUser.getFullName());
			// Setting group and groupName
			topic.setGroup(group);
			topic.setGroupDisplayName(group.getDisplayName());
			// Setting topicStatus as OPEN
			topic.setTopicStatus(TopicStatus.OPEN);
            // Setting FB User ID into Topic 
			topic.setOwnerFbPhotoId(ownerUser.getFbUserId());
			// Creating the topic in DB
			topic = topicService.createTopic(topic);

			// Once topic has been created create topicChatRoom with ownerUserId
			// as owner
			createXmppTopicChatRoomWithOwner(topic, ownerUserId,
					xmppTopicService);

			if(userIdToObjectArrays.size() > 0)
			{
				Thread t1 = new Thread(new TopicJoinThread(topic.getId(), XmppChatRoomRole.members, userIdToObjectArrays));
			     t1.start();
			}
			// Setting values of topicTo from topic
			topicUtils.setTopicToFromTopic(topicTo, topic);
			// Setting isOwnerUser as true
			topicTo.setOwnerUser(true);

			// Returning the updated topicTo
			data = topicTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	// Also updates the topicViewCount and lastViewTime
	@Transactional
	@RequestMapping(value = "/joinTopicChat/{userId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response joinTopicChat(@RequestBody TopicTo topicTo,
			@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;
		try {
			int topicId = topicTo.getId();
			// Fetching topic from DB
			Topic topic = topicService.getTopic(topicId);

			// Checking if the user exists in DB
			userService.getUser(userId);

			// Checking if the combination of userId and topicTo is valid
			int groupId = topicTo.getGroupId();
			groupUserService.findByUserIdAndGroupId(userId, groupId);

			// Fetching topicViewCount
			int topicViewCount = topic.getTopicViewCount();
			topicViewCount++;

			// Updating topicViewCount
			topic.setTopicViewCount(topicViewCount);

			// Updating lastViewTime
			topic.setLastViewTime(new Date());

			// Updating the topic in DB
			topic = topicService.updateTopic(topic);

			// Adding the user to topic chat on XMPP server
			if (topicTo.isOwnerUser()) {
				addOwnerToTopicChat(topicId, userId, xmppTopicService);
			} else {
				addMemberToTopicChat(topicId, userId, xmppTopicService);
			}

			// Updating topicTo from topic
			topicUtils.setTopicToFromTopic(topicTo, topic);

			// Returning topicTo
			data = topicTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/leaveTopicChat/{userId}", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response leaveTopicChat(@RequestBody TopicTo topicTo,
			@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;
		try {
			int topicId = topicTo.getId();
			// Checking if topic with the specified Id exists
			topicService.getTopic(topicId);

			// Checking if the user exists in DB
			userService.getUser(userId);

			// Checking if the combination of userId and topicTo is valid
			int groupId = topicTo.getGroupId();
			groupUserService.findByUserIdAndGroupId(userId, groupId);

			// Removing the user from topic chat on XMPP server
			// Remove only if user is not owner of the topic
			if (!topicTo.isOwnerUser()) {
				deleteMemberFromTopicChat(topicId, userId, xmppTopicService);
			} else {
				throw new ApplicationException(
						XmppConstants.ERROR_MSG_OWNER_CAN_NOT_LEAVE_TOPIC);
			}

			// Returning success response
			data = Constants.SUCCESS_MSG_LEAVE_TOPIC;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/likeTopic", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response likeTopic(@RequestBody UserIdTo userIdTo) {
		boolean success = false;
		Object data = null;
		try {
			int topicId = userIdTo.getId();
			// Fetching the topic from DB
			
			Topic topic = topicService.getTopic(topicId);

			// Fetching the topicLikeCount
			int topicLikeCount = topic.getTopicLikeCount();
			topicLikeCount++;

			// Updating the topicLikeCount
			topic.setTopicLikeCount(topicLikeCount);

			// Updating the topic in DB
			topic = topicService.updateTopic(topic);

			// Instantiating topicTo to be returned
			TopicTo topicTo = new TopicTo();

			// Populating topicTo from topic
			topicUtils.setTopicToFromTopic(topicTo, topic);

			// Returning topicTo
			data = topicTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/spamTopic", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response spamTopic(@RequestBody UserIdTo userIdTo) {
		boolean success = false;
		Object data = null;
		try {
			int topicId = userIdTo.getId();
			// Fetching the topic from DB
			Topic topic = topicService.getTopic(topicId);

			// Fetching the topicSpamCount
			int topicSpamCount = topic.getTopicSpamCount();
			topicSpamCount++;

			// Updating the topicSpamCount
			topic.setTopicSpamCount(topicSpamCount);

			// Updating the topic in DB
			topic = topicService.updateTopic(topic);

			// Instantiating topicTo to be returned
			TopicTo topicTo = new TopicTo();

			// Populating topicTo from topic
			topicUtils.setTopicToFromTopic(topicTo, topic);

			// Returning topicTo
			data = topicTo;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getTopicTosByUserId/{userId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getTopicTosByUserId(@PathVariable("userId") int userId) {
		boolean success = false;
		Object data = null;

		try {
			// Fetching the list of all groupIds applicable to the userId
			List<Integer> groupIds = groupUserService
					.findAllGroupIdsByUserId(userId);

			// Fetching the required data from DB as a List of Object[]
			List<Object[]> topicToObjectArrays = topicService
					.findAllTopicTosByGroupIds(groupIds);

			// Creating instance of topicTos to be returned
			List<TopicTo> topicTos = new ArrayList<>();

			// Populating topicTos from topicToObjectArrays
			for (Object[] topicToObjectArray : topicToObjectArrays) {

				// Instantiation topicTo for each iteration
				TopicTo topicTo = new TopicTo();

				// Initializing the topicTo
				setTopicToFromTopicToObjectArray(topicTo, topicToObjectArray);

				// Setting appropriate value of isOwnerUser
				Integer userIdInteger = userId; // Auto-boxing
				if (userIdInteger.equals(topicTo.getOwnerUserId())) {
					topicTo.setOwnerUser(true);
				}

				// Adding the instance of topicTo to topicTos
				topicTos.add(topicTo);
			}
			// Returning the List of topicTos
			data = topicTos;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getTopicTosByOwnerUserId/{ownerUserId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getTopicTosByOwnerUserId(
			@PathVariable("ownerUserId") int ownerUserId) {
		boolean success = false;
		Object data = null;

		try {
			// Fetching the required data from DB as a List of Object[]
			List<Object[]> topicToObjectArrays = topicService
					.findAllTopicTosByOwnerUserId(ownerUserId);

			// Creating instance of topicTos to be returned
			List<TopicTo> topicTos = new ArrayList<>();

			// Populating topicTos from topicToObjectArrays
			for (Object[] topicToObjectArray : topicToObjectArrays) {

				// Instantiation topicTo for each iteration
				TopicTo topicTo = new TopicTo();

				// Initializing the topicTo
				setTopicToFromTopicToObjectArray(topicTo, topicToObjectArray);

				// Setting isOwnerUser as true
				topicTo.setOwnerUser(true);

				// Adding the instance of topicTo to topicTos
				topicTos.add(topicTo);
			}
			// Returning the List of topicTos
			data = topicTos;
			success = true;
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	@Transactional
	@RequestMapping(value = "/getTopicListByGroupeCategory", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response getTopicTosByOwnerUserId(@RequestBody GroupCategoryTo groupCategory) {
		boolean success = false;
		Object data = null;

		String groupCategoryName = groupCategory.getGroupCategory();
		// Fetching the required data from DB as a List of Object[]
		List<String> topicListToObjectArrays = topicService
				.findAllTopicListByGroupCategory(groupCategoryName);

		// Creating instance of topicTos to be returned
//		List<String> topicListTos = new ArrayList<>();
//		for (Object[] topicToObjectArray : topicListToObjectArrays) {
//			System.out.println(topicToObjectArray[0]);
//			
//			topicListTos.add(topicToObjectArray[0].toString());
//		}
		// Returning the List of topicTos
		groupCategory.setTopicTypeList(topicListToObjectArrays);
		data = groupCategory;
		success = true;
		return new Response(success, data);
	}

	// TODO: Move all methods below this method to a new class
	// TopicControllerUtils
	private void setTopicToFromTopicToObjectArray(TopicTo topicTo,
			Object[] topicToObjectArray) {
		topicTo.setId((int) topicToObjectArray[0]);
		topicTo.setType((String) topicToObjectArray[1]);
		topicTo.setName((String) topicToObjectArray[2]);
		topicTo.setGroupId((int) topicToObjectArray[3]);
		topicTo.setOwnerUserId((int) topicToObjectArray[4]);
		topicTo.setGroupDisplayName((String) topicToObjectArray[5]);
		topicTo.setOwnerUserName((String) topicToObjectArray[6]);
		topicTo.setDescription((String) topicToObjectArray[7]);
		//topicTo.setImagePath((String) topicToObjectArray[8]);

		// Converting String to enum TopicStatus
		String topicStatusStr = (String) topicToObjectArray[9];
		topicTo.setTopicStatus(TopicStatus.valueOf(topicStatusStr));

		topicTo.setTopicLikeCount((int) topicToObjectArray[10]);
		topicTo.setTopicSpamCount((int) topicToObjectArray[11]);
		topicTo.setTopicViewCount((int) topicToObjectArray[12]);
		topicTo.setLastViewTime((Date) topicToObjectArray[13]);
		topicTo.setCreationTime((Date) topicToObjectArray[14]);
		topicTo.setPhotoId((int) topicToObjectArray[15]);
		topicTo.setOwnerFbId((String) topicToObjectArray[16]);
		topicTo.setImagePath(Constants.PTOTOCOL+"://"+Constants.HOST+"/"+Constants.APPLICATION_NAME+"/"+Constants.SERVICE_NAME+"/"+(int) topicToObjectArray[15]);
	}

	private XmppChatRoomTo createXmppTopicChatRoomWithOwner(Topic topic,
			int ownerUserId, XmppTopicService xmppTopicService)
			throws ApplicationException {

		// Instantiating XmppChatRoomTo
		XmppChatRoomTo xmppChatRoomTo = newXmppChatRoomTo(topic, ownerUserId);

		// Create topicChatRoom on XMPP server
		xmppTopicService.createTopicChat(xmppChatRoomTo);

		return xmppChatRoomTo;
	}

	private XmppChatRoomTo newXmppChatRoomTo(Topic topic, int ownerUserId) {

		// Instantiating XmppChatRoomTo
		XmppChatRoomTo xmppChatRoomTo = new XmppChatRoomTo();

		// Initializing xmppChatRoomTo
		String idStr = String.valueOf(topic.getId());
		xmppChatRoomTo.setDescription(topic.getDescription());
		xmppChatRoomTo.setNaturalName(topic.getName());

		// Instantiating owners
		Owners owners = new Owners();
		// Adding ownerUserId to List<String> owner
		List<String> owner = owners.getOwner();
		String ownerUserIdStr = String.valueOf(ownerUserId)
				+ XmppConstants.AT_THE_RATE_NEARGROUP;
		owner.add(ownerUserIdStr);
		// Setting owners of xmppChatRoomTo
		xmppChatRoomTo.setOwners(owners);

		xmppChatRoomTo.setRoomName(idStr);
		// TODO: Change subject if needed
		xmppChatRoomTo.setSubject(topic.getDescription());

		return xmppChatRoomTo;
	}

	private void addOwnerToTopicChat(int topicId, int ownerUserId,
			XmppTopicService xmppTopicService) throws ApplicationException {
		String roomName = String.valueOf(topicId);
		String userName = String.valueOf(ownerUserId)
				+ XmppConstants.AT_THE_RATE_NEARGROUP;

		xmppTopicService.addOwnerToTopicChat(roomName, userName);
	}

	private void addMemberToTopicChat(int topicId, int memberUserId,
			XmppTopicService xmppTopicService) throws ApplicationException {
		String roomName = String.valueOf(topicId);
		String userName = String.valueOf(memberUserId)
				+ XmppConstants.AT_THE_RATE_NEARGROUP;

		xmppTopicService.addMemberToTopicChat(roomName, userName);
	}

	private void deleteOwnerFromTopicChat(int topicId, int ownerUserId,
			XmppTopicService xmppTopicService) throws ApplicationException {
		String roomName = String.valueOf(topicId);
		String userName = String.valueOf(ownerUserId)
				+ XmppConstants.AT_THE_RATE_NEARGROUP;

		xmppTopicService.deleteOwnerFromTopicChat(roomName, userName);
	}

	private void deleteMemberFromTopicChat(int topicId, int memberUserId,
			XmppTopicService xmppTopicService) throws ApplicationException {
		String roomName = String.valueOf(topicId);
		String userName = String.valueOf(memberUserId)
				+ XmppConstants.AT_THE_RATE_NEARGROUP;

		xmppTopicService.deleteMemberFromTopicChat(roomName, userName);
	}
}
