package com.skt.web.alpha.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skt.web.alpha.Thread.TopicJoinThreadForNewUsers;
import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.Gender;
import com.skt.web.alpha.constants.GroupGenderCategory;
import com.skt.web.alpha.constants.GroupIconCategory;
import com.skt.web.alpha.constants.UserGroupRole;
import com.skt.web.alpha.constants.UserGroupStatus;
import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.model.Group;
import com.skt.web.alpha.model.GroupUser;
import com.skt.web.alpha.model.Locality;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.GroupService;
import com.skt.web.alpha.service.GroupUserService;
import com.skt.web.alpha.service.LocalityService;
import com.skt.web.alpha.service.TopicService;
import com.skt.web.alpha.service.XmppChatRoomService;
import com.skt.web.alpha.service.XmppUserService;
import com.skt.web.alpha.to.XmppChatRoomTo;
import com.skt.web.alpha.to.XmppUserTo;
import com.skt.web.common.exception.ApplicationException;

@Component
public class VerificationControllerUtils {

	private static final Logger LOG = Logger
			.getLogger(VerificationControllerUtils.class);
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	LocalityService localityService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	XmppChatRoomService xmppChatRoomService;
	
	@Autowired
	GroupUserService groupUserService;
	
	@Autowired
	UserUtils userUtils;
	
	@Autowired
	Gson gson;
	
	@Autowired
	TopicService topicService;
	
	public void groupsJoiningLogicForUser(User user)
			throws ApplicationException {
		
		String invitationCode;
		String location;
		String city;
		String groupName;
		String displayName;
        String locality = user.getLocality();
		// Group 1: Location-Age
        //int age = commonUtils.calculateAge(user.getDob());
		city = user.getCity();
		invitationCode = user.getInvitationCode();
        try
        {
        	Locality localityModal = localityService.getLocalityByLocation(locality.trim(),city.trim());
        	location = localityModal.getLocation();
        }catch(ApplicationException e){
        	LOG.info("Unale to find locality for locality ["+locality+"]"+" city ["+city+"]");
        	location = user.getLocation();
        }
		// Join location group only if location is provided and invitationCode
		// matches AchieversCode
		if (location != null && !location.equals(Constants.CURRENT_LOCATION)
				&& Constants.ACHIEVERS_CODE.equals(invitationCode)) {
//			if (age <= Constants.FOURTY_INTEGER) {
//				groupName = location + Constants.GROUP_NAME_SEPARATOR
//						+ Constants.ZERO_TO_FOURTY_STRING;
//			} else {
//				groupName = location + Constants.GROUP_NAME_SEPARATOR
//						+ Constants.FOURTY_ABOVE_STRING;
//			}
			groupName = Constants.YOUNG_ACHIEVERS + location + Constants.IN + city;
			displayName = Constants.YOUNG_ACHIEVERS + Constants.NEAR_BY;

			// Create a group instance after persisting required entries in DB
			fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.YOUNG_ACHIEVER_GROUP, displayName,
					GroupIconCategory.LOCATION, GroupGenderCategory.UNISEX,
					groupService, xmppChatRoomService, user, groupUserService);
		}

		// Group 2: Locality-Age Group
		
		
		if (locality != null
				&& !locality.equals(Constants.LOCALITY_OR_APARTMENT)) {
//			if (age <= Constants.FOURTY_INTEGER) {
//				groupName = locality + Constants.IN + location+Constants.GROUP_NAME_SEPARATOR
//						+ Constants.ZERO_TO_FOURTY_STRING;
//			} else {
//				groupName = locality + Constants.IN + location+Constants.GROUP_NAME_SEPARATOR
//						+ Constants.FOURTY_ABOVE_STRING;
//			}
			groupName = locality + Constants.IN + city;
			displayName = locality + Constants.RESIDENTS;

			// Create a group instance after persisting required entries in DB
			fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.LOCALITY_GROUP, displayName,
					GroupIconCategory.LOCALITY, GroupGenderCategory.UNISEX,
					groupService, xmppChatRoomService, user, groupUserService);
		}

		// Group 3 (or 4): Native Location Group
		String nativeLocation = user.getNativeLocation();
		if (location != null && nativeLocation != null
				&& !location.equals(Constants.CURRENT_LOCATION)
				&& !nativeLocation.equals(Constants.NATIVE_LOCATION)
				&& !nativeLocation.equalsIgnoreCase(Constants.AMERICA)) {
			groupName = nativeLocation + Constants.GROUP_NAME_SEPARATOR 
					+ location + Constants.IN + city;
			displayName = nativeLocation + Constants.SPACE + Constants.NEAR_BY;

			// Decide GroupIconCategory on the basis of countryCode
			GroupIconCategory groupIconCategory;
			String countryCode = user.getCountryCode();
			if (countryCode.equals(Constants.COUNTRY_CODE_INDIA)) {
				groupIconCategory = GroupIconCategory.INDIA_ORIGIN;
			} else {
				groupIconCategory = GroupIconCategory.US_ORIGIN;
			}
			// Create a group instance after persisting required entries in DB
			fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.NATIVE_LOCATION_GROUP, displayName,
					groupIconCategory, GroupGenderCategory.UNISEX,
					groupService, xmppChatRoomService, user, groupUserService);
		}

		// Group 5-11: Location-Interest Group(s)
		List<String> interests = gson.fromJson(user.getInterests(),
				new TypeToken<List<String>>() {
				}.getType());
		if (interests != null) {
			for (String interest : interests) {
				groupName = location + Constants.GROUP_NAME_SEPARATOR
						+ interest + Constants.SPACE + city;
				if(interest.indexOf("Nearby") >= 0)
				{
				displayName = interest;
				}
				else
				{
					displayName = interest + Constants.SPACE + Constants.NEAR_BY;
				}
				
                String interestGroupCategory = userUtils.getGroupCategoryBasedOnInterest(interest);
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName,interestGroupCategory, displayName,
						GroupIconCategory.INTERESTS,
						GroupGenderCategory.UNISEX, groupService,
						xmppChatRoomService, user, groupUserService);
			}
		}

		// Group 12: Location-JobType group
		String jobType = user.getJobType();
		if (location != null && jobType != null
				&& !location.equals(Constants.CURRENT_LOCATION)
				&& !jobType.equals(Constants.JOB_TYPE)) {
			groupName = jobType + Constants.GROUP_NAME_SEPARATOR + location + Constants.IN + city;
			displayName = jobType + Constants.SPACE + Constants.NEAR_BY;

			String jobTypeGroupCategory = userUtils.getJobTypeGroupCategory(jobType,invitationCode);
			if(jobTypeGroupCategory.equalsIgnoreCase(Constants.COLLEGE_STUDENT))
			{
				String college = Constants.COLLEGE_CODE_MAP.get(invitationCode);
					groupName = college;
					displayName = college + Constants.GROUP;
					// Create a group instance after persisting required entries in
					// DB
					fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.COLLEGE_STUDENT, displayName,
							GroupIconCategory.COLLEGE, GroupGenderCategory.UNISEX,
							groupService, xmppChatRoomService, user,
							groupUserService);
				
			}
			else if(jobTypeGroupCategory.equalsIgnoreCase(Constants.SCHOOL_STUDENT))
			{
				String school = Constants.SCHOOL_CODE_MAP.get(invitationCode);
				groupName = school;
				displayName = school + Constants.GROUP;
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.SCHOOL_STUDENT, displayName,
						GroupIconCategory.SCHOOL, GroupGenderCategory.UNISEX,
						groupService, xmppChatRoomService, user,
						groupUserService);	
		}else if(jobType != null && !jobType.equalsIgnoreCase("Student: School") && !jobType.equalsIgnoreCase("Student: College"))
			{
			// Create a group instance after persisting required entries in DB
			fetchOrCreateGroupAndCreateGroupUser(groupName,jobTypeGroupCategory, displayName,
					GroupIconCategory.JOB_TYPE, GroupGenderCategory.UNISEX,
					groupService, xmppChatRoomService, user, groupUserService);
			}
		}

		// Group 13: City-Industry group
		city = user.getCity();
		String industry = user.getIndustry();
		if (city != null && industry != null
				&& !industry.equals(Constants.INDUSTRY)
				&& !industry.equals(Constants.NONE_OR_OTHER)) {
			groupName = industry + Constants.GROUP_NAME_SEPARATOR + location + Constants.IN + city;
			displayName = industry + Constants.NEAR_BY;

			// Create a group instance after persisting required entries in DB
			String industryroupCategory = userUtils.getIndustryGroupCategory(jobType);
			
			fetchOrCreateGroupAndCreateGroupUser(groupName,industryroupCategory, displayName,
					GroupIconCategory.INDUSTRY, GroupGenderCategory.UNISEX,
					groupService, xmppChatRoomService, user, groupUserService);
		}

		// Group 14: Location-Girls-Age
		if (location != null && !location.equals(Constants.CURRENT_LOCATION)) {
			Gender gender = user.getGender();
			// Add to this group only is user is a FEMALE
			if (gender.equals(Gender.FEMALE)) {
//				if (age <= Constants.THIRTY_FIVE_INTEGER) {
//					groupName = location + Constants.GROUP_NAME_SEPARATOR
//							+ Constants.GIRLS + Constants.GROUP_NAME_SEPARATOR
//							+ Constants.ZERO_TO_THIRTY_FIVE_STRING;
//				} else {
//					groupName = location + Constants.GROUP_NAME_SEPARATOR
//							+ Constants.GIRLS + Constants.GROUP_NAME_SEPARATOR
//							+ Constants.THIRTY_FIVE_ABOVE_STRING;
//				}
				groupName = Constants.ONLY + Constants.GIRLS + Constants.IN + location + Constants.GROUP_NAME_SEPARATOR
						+ city;
				
				displayName = Constants.ONLY + Constants.GIRLS + Constants.SPACE + Constants.NEAR_BY;
				String grilsOnlyGroupCategory = userUtils.getGrilsOnlyGroupCategory(jobType);
				
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName,grilsOnlyGroupCategory, displayName,
						GroupIconCategory.GIRLS, GroupGenderCategory.FEMALE,
						groupService, xmppChatRoomService, user,
						groupUserService);
			}
		}

		// Group 15 (or 16) College Group (or College-City Group)
		String college = Constants.COLLEGE_CODE_MAP.get(invitationCode);
		if (college != null && !jobType.equalsIgnoreCase("Student: School") && !jobType.equalsIgnoreCase("Student: College")) {
			// Set the value of college for user
			// TODO: Known issue
			// TODO: user.setCollege(college);

			/*if (Constants.STUDENT_COLLEGE.equals(jobType)) {
				groupName = college;
				displayName = college + Constants.GROUP;
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.SCHOOL_STUDENT, displayName,
						GroupIconCategory.COLLEGE, GroupGenderCategory.UNISEX,
						groupService, xmppChatRoomService, user,
						groupUserService);
			} else*/ if (city != null) {
				groupName = college + Constants.GROUP_NAME_SEPARATOR + city;
				displayName = college + Constants.ALUMNI_IN + city;
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName, Constants.COLLEGE_ALUMNI, displayName,
						GroupIconCategory.COLLEGE, GroupGenderCategory.UNISEX,
						groupService, xmppChatRoomService, user,
						groupUserService);
			}
		}

		// TODO: Group 17 (or 18) School Group (or School-City Group)
		String school = Constants.SCHOOL_CODE_MAP.get(invitationCode);
		if (school != null && !jobType.equalsIgnoreCase("Student: School") && !jobType.equalsIgnoreCase("Student: College")) {
			// Set the value of college for user
			// TODO: Known issue
			// TODO: user.setCollege(college);

			/*if (Constants.STUDENT_COLLEGE.equals(jobType)) {
				groupName = college;
				displayName = college + Constants.GROUP;
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName,Constants.SCHOOL_STUDENT, displayName,
						GroupIconCategory.COLLEGE, GroupGenderCategory.UNISEX,
						groupService, xmppChatRoomService, user,
						groupUserService);
			} else*/ if (city != null) {
				groupName = school + Constants.GROUP_NAME_SEPARATOR + city;
				displayName = school + Constants.ALUMNI_IN + city;
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName, Constants.SCHOOL_ALUMNI, displayName,
						GroupIconCategory.SCHOOL, GroupGenderCategory.UNISEX,
						groupService, xmppChatRoomService, user,
						groupUserService);
			}
		}

		// TODO: Group 19 Company Group

		String company = Constants.CORPORATE_CODE_MAP.get(invitationCode);
		if (company != null) {
				groupName = company;
				displayName = company + Constants.GROUP;
				// Create a group instance after persisting required entries in
				// DB
				fetchOrCreateGroupAndCreateGroupUser(groupName, Constants.COMPANY_GROUP, displayName,
						GroupIconCategory.COMPANY, GroupGenderCategory.UNISEX,
						groupService, xmppChatRoomService, user,
						groupUserService);
			}
		
	}

	private void fetchOrCreateGroupAndCreateGroupUser(String groupName,String groupCategoryName,
			String displayName, GroupIconCategory groupIconCategory,
			GroupGenderCategory groupGenderCategory, GroupService groupService,
			XmppChatRoomService xmppChatRoomService, User user,
			GroupUserService groupUserService) throws ApplicationException {

		// Fetch or create group in DB
		Group group = fetchOrCreateGroup(groupName, groupCategoryName,displayName,
				groupIconCategory, groupGenderCategory, groupService,
				xmppChatRoomService,user);

		// Create an instance of groupUser
		GroupUser groupUser = newGroupUser(group, user);

		// Persist the groupUser instance in groups_users table
		groupUser = groupUserService.createGroupUser(groupUser);

		// Add the corresponding xmppUser to the corresponding xmppChatRoom
		String roomName = String.valueOf(group.getId());
		String userName = String.valueOf(user.getId());
		xmppChatRoomService.addMemberToChatRoom(roomName, userName);
	}

	private GroupUser newGroupUser(Group group, User user) {
		// Instantiate and populate groupUser
		GroupUser groupUser = new GroupUser();
		groupUser.setGroup(group);
		groupUser.setGroupDisplayName(group.getDisplayName());
		groupUser.setUser(user);
		groupUser.setUserName(user.getFullName());
		groupUser.setUserGroupRole(UserGroupRole.USER);
		groupUser.setUserGroupStatus(UserGroupStatus.ACTIVE);

		return groupUser;
	}

	private Group fetchOrCreateGroup(String groupName,String groupCategoryName, String displayName,
			GroupIconCategory groupIconCategory,
			GroupGenderCategory groupGenderCategory, GroupService groupService,
			XmppChatRoomService xmppChatRoomService,User user)
			throws ApplicationException {

		Group group;
		int userCount = 0;
		try {
			// Fetch group from DB
			group = groupService.findByName(groupName);
			userCount = group.getUserCount();
			group.setUserCount(++userCount);
			List<Integer> topicList = topicService.findAllTopicListByGroupId(group.getId());
			if(topicList != null && topicList.size() > 0)
			{
				Thread t1 = new Thread(new TopicJoinThreadForNewUsers(user.getId(), XmppChatRoomRole.members, topicList));
				t1.start();
			}
			groupService.updateGroup(group);
		} catch (ApplicationException e) {
			// Create group if not present
			group = newGroup(groupName,groupCategoryName, displayName, groupIconCategory,
					groupGenderCategory);
			group = groupService.createGroup(group);

			
			// Creating the new chatRoom on XMPP server also
			createXmppChatRoom(group, xmppChatRoomService);
		}

		return group;
	}

	private Group newGroup(String groupName,String groupCategoryName, String displayName,
			GroupIconCategory groupIconCategory,
			GroupGenderCategory groupGenderCategory) {
		// Instantiate and populate group
		Group group = new Group();
		group.setName(groupName);
		group.setDisplayName(displayName);
		// TODO: Change group description
		group.setDescription(displayName);
		group.setGroupIconCategory(groupIconCategory);
		group.setGroupCategory(groupCategoryName);
		group.setUserCount(1);
		// Setting groupGenderCategory if != null
		if (groupGenderCategory != null) {
			group.setGroupGenderCategory(groupGenderCategory);
		}

		return group;
	}

	public void createXmppUser(User user, XmppUserService xmppUserService)
			throws ApplicationException {
		// Instantiating XmppUserTo
		XmppUserTo xmppUserTo = newXmppUserTo(user);

		// Creating the user on XMPP server
		xmppUserService.createUser(xmppUserTo);
	}

	private XmppUserTo newXmppUserTo(User user) {
		// Instantiating XmppUserTo
		XmppUserTo xmppUserTo = new XmppUserTo();

		// Initializing xmppUserTo from user
		String idStr = String.valueOf(user.getId());
		xmppUserTo.setUsername(idStr);
		xmppUserTo.setPassword(idStr);
		xmppUserTo.setName(user.getFullName());
		xmppUserTo.setEmail(user.geteMail());

		return xmppUserTo;
	}

	private void createXmppChatRoom(Group group,
			XmppChatRoomService xmppChatRoomService)
			throws ApplicationException {
		// Instantiating and initializing XmppChatRoomTo
		XmppChatRoomTo xmppChatRoomTo = newXmppChatRoomTo(group);

		// Creating the new chatRoom on XMPP server also
		xmppChatRoomService.createChatRoom(xmppChatRoomTo);
	}

	private XmppChatRoomTo newXmppChatRoomTo(Group group) {
		// Instantiating XmppChatRoomTo
		XmppChatRoomTo xmppChatRoomTo = new XmppChatRoomTo();

		// Initializing xmppChatRoomTo from group
		String idStr = String.valueOf(group.getId());
		xmppChatRoomTo.setDescription(group.getDescription());
		xmppChatRoomTo.setNaturalName(group.getName());
		xmppChatRoomTo.setRoomName(idStr);
		xmppChatRoomTo.setSubject(group.getDisplayName());

		return xmppChatRoomTo;
	}

}
