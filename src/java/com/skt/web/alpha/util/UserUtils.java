package com.skt.web.alpha.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.DeviceType;
import com.skt.web.alpha.model.Registration;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.to.FbEducationHistory;
import com.skt.web.alpha.to.FbGroup;
import com.skt.web.alpha.to.FbLike;
import com.skt.web.alpha.to.FbPost;
import com.skt.web.alpha.to.FbWorkHistory;
import com.skt.web.alpha.to.UserTo;
import com.skt.web.alpha.to.VerificationTo;

@Component
public class UserUtils {

	@Autowired
	Gson gson;

	@Autowired
	CommonUtils commonUtils;

	public void setUserFromRegistration(User user, Registration registration) {
		user.setAboutMe(registration.getAboutMe());
		user.setCountryCode(registration.getCountryCode());
		user.setCoverPhotoUrl(registration.getCoverPhotoUrl());
		user.setDeviceModel(registration.getDeviceModel());
		user.setDeviceType(registration.getDeviceType());
		user.setDob(registration.getDob());
		user.setFbEducationHistory(registration.getFbEducationHistory());
		user.seteMail(registration.geteMail());
		//user.setFbLikes(registration.getFbLikes());
		user.setFbGroups(registration.getFbGroups());
		user.setFbCreationDate(registration.getFbCreationDate());
		user.setFbCurrentLocation(registration.getFbCurrentLocation());
		
		user.setFbRelationsShips(registration.getFbRelationsShips());
		
		user.setFbTimelineUrl(registration.getFbTimelineUrl());
		user.setFbUserId(registration.getFbUserId());
		user.setFbWorkHistory(registration.getFbWorkHistory());
		user.setFirstName(registration.getFullName());
		user.setFullName(registration.getFullName());
		user.setGender(registration.getGender());
		user.setImeiNo(registration.getImeiNo());
		user.setInvitationCode(registration.getInvitationCode());
		user.setLastName(registration.getLastName());
		user.setLocale(registration.getLocale());
		user.setManufacturer(registration.getManufacturer());
		user.setMaritalStatus(registration.getMaritalStatus());
		user.setMobileNo(registration.getMobileNo());
		user.setNoOfFriends(registration.getNoOfFriends());
		user.setOsVersion(registration.getOsVersion());
		//user.setFbPosts(registration.getFbPosts());
		user.setPushId(registration.getPushId());
		user.getRegistrations().add(registration);
	}

	public void setUserFromVerificationTo(User user,
			VerificationTo verificationTo) {
		user.setCity(verificationTo.getCity());
		user.setLocation(verificationTo.getLocation());
		user.setLocality(verificationTo.getLocality());
		user.setNativeLocation(verificationTo.getNativeLocation());
		user.setInterests(gson.toJson(verificationTo.getInterests()));
		user.setJobType(verificationTo.getJobType());
		user.setIndustry(verificationTo.getIndustry());
	}

	public void setUserFromUserTo(User user, UserTo userTo) {
		user.setAboutMe(userTo.getAboutMe());
		user.setCity(userTo.getCity());
		user.setCollege(userTo.getCollege());
		user.setCompany(userTo.getCompany());
		user.setCountryCode(userTo.getCountryCode());
		user.setCoverPhotoUrl(userTo.getCoverPhotoUrl());
		user.setDeviceModel(userTo.getDeviceModel());
		// Converting int to enum DeviceType
		user.setDeviceType(DeviceType.values()[userTo.getDeviceType()]);
		user.setDob(userTo.getDob());
		user.seteMail(userTo.geteMail());
		user.setFbEducationHistory(gson.toJson(userTo.getFbEducationHistory()));
		//user.setFbLikes(gson.toJson(userTo.getFbLikes()));
		user.setFbGroups(gson.toJson(userTo.getFbGroups()));
		user.setFbCreationDate(userTo.getFbCreationDate());
		user.setFbCurrentLocation(userTo.getFbCurrentLocation());
		user.setFbRelationsShips(userTo.getFbRelationsShips());
		
		user.setFbTimelineUrl(userTo.getFbTimelineUrl());
		user.setFbUserId(userTo.getFbUserId());
		user.setFbWorkHistory(gson.toJson(userTo.getFbWorkHistory()));
		user.setFirstName(userTo.getFirstName());
		user.setFullName(userTo.getFullName());
		user.setGender(userTo.getGender());
		user.setId(userTo.getId());
		user.setImeiNo(userTo.getImeiNo());
		user.setIndustry(userTo.getIndustry());
		user.setInterests(gson.toJson(userTo.getInterests()));
		user.setInvitationCode(userTo.getInvitationCode());
		user.setJobType(userTo.getJobType());
		user.setLastName(userTo.getLastName());
		user.setLocale(userTo.getLocale());
		user.setLocality(userTo.getLocality());
		user.setLocation(userTo.getLocation());
		user.setManufacturer(userTo.getManufacturer());
		user.setMaritalStatus(userTo.getMaritalStatus());
		user.setMobileNo(userTo.getMobileNo());
		user.setNativeLocation(userTo.getNativeLocation());
		user.setNoOfFriends(userTo.getNoOfFriends());
		user.setOsVersion(userTo.getOsVersion());
		//user.setFbPosts(gson.toJson(userTo.getFbPosts()));
		user.setPushId(userTo.getPushId());
		// TODO: user.setRegistrations(userTo.getRegistrations());
		user.setSchool(userTo.getSchool());
		user.setUserLikeCount(userTo.getUserLikeCount());
		user.setUserScore(userTo.getUserScore());
		user.setUserSpamCount(userTo.getUserSpamCount());
		user.setUserStatus(userTo.getUserStatus());
	}

	public void setUserToFromUser(UserTo userTo, User user) {
		userTo.setAboutMe(user.getAboutMe());
		userTo.setCity(user.getCity());
		userTo.setCollege(user.getCollege());
		userTo.setCompany(user.getCompany());
		userTo.setCountryCode(user.getCountryCode());
		userTo.setCoverPhotoUrl(user.getCoverPhotoUrl());
		userTo.setDeviceModel(user.getDeviceModel());
		// Converting enum DeviceType to int value
		userTo.setDeviceType(user.getDeviceType().ordinal());
		userTo.setDob(user.getDob());
		userTo.seteMail(user.geteMail());
		userTo.setFbEducationHistory(gson.fromJson(
				user.getFbEducationHistory(),
				new TypeToken<List<FbEducationHistory>>() {
				}.getType()));
//		userTo.setFbLikes(gson.fromJson(user.getFbLikes(),
//				new TypeToken<List<FbLike>>() {
//				}.getType()));
		//TODO : [RR] Need to uncomment and check that casting error
//		userTo.setFbGroups(gson.fromJson(user.getFbGroups(),
//				new TypeToken<List<FbGroup>>() {
//				}.getType()));
				
		userTo.setFbCreationDate(user.getFbCreationDate());
		userTo.setFbCurrentLocation(user.getFbCurrentLocation());
		userTo.setFbRelationsShips(user.getFbRelationsShips());
		userTo.setFbTimelineUrl(user.getFbTimelineUrl());
		userTo.setFbUserId(user.getFbUserId());
		userTo.setFbWorkHistory(gson.fromJson(user.getFbWorkHistory(),
				new TypeToken<List<FbWorkHistory>>() {
				}.getType()));
		userTo.setFirstName(user.getFirstName());
		userTo.setFullName(user.getFullName());
		userTo.setGender(user.getGender());
		userTo.setId(user.getId());
		userTo.setImeiNo(user.getImeiNo());
		userTo.setIndustry(user.getIndustry());
		userTo.setInterests(gson.fromJson(user.getInterests(),
				new TypeToken<List<String>>() {
				}.getType()));
		userTo.setInvitationCode(user.getInvitationCode());
		userTo.setJobType(user.getJobType());
		userTo.setLastName(user.getLastName());
		userTo.setLocale(user.getLocale());
		userTo.setLocality(user.getLocality());
		userTo.setLocation(user.getLocation());
		userTo.setManufacturer(user.getManufacturer());
		userTo.setMaritalStatus(user.getMaritalStatus());
		userTo.setMobileNo(user.getMobileNo());
		userTo.setNativeLocation(user.getNativeLocation());
		userTo.setNoOfFriends(user.getNoOfFriends());
		userTo.setOsVersion(user.getOsVersion());
//		userTo.setFbPosts(gson.fromJson(user.getFbPosts(),
//				new TypeToken<List<FbPost>>() {
//				}.getType()));
		userTo.setPushId(user.getPushId());
		// TODO: userTo.setRegistrations(user.getRegistrations());
		userTo.setSchool(user.getSchool());
		userTo.setUserCreationTime(user.getUserCreationTime());
		userTo.setUserLikeCount(user.getUserLikeCount());
		userTo.setUserScore(user.getUserScore());
		userTo.setUserSpamCount(user.getUserSpamCount());
		userTo.setUserStatus(user.getUserStatus());
	}

	public String getJobTypeGroupCategory(String jobType,String invitationCode) {
		
		if(jobType != null && jobType.equalsIgnoreCase("Student: School") && Constants.SCHOOL_CODE_MAP.get(invitationCode == null ? -1:invitationCode) != null)
		{
			return Constants.SCHOOL_STUDENT;
		}
		else if(jobType != null && jobType.equalsIgnoreCase("Student: College") && Constants.COLLEGE_CODE_MAP.get(invitationCode == null ? -1:invitationCode) != null)
		{
			return Constants.COLLEGE_STUDENT;
		}
		else
		{
			return Constants.JOB_PROFILE_GROUP;
		}
		
	}

	public String getIndustryGroupCategory(String jobType) {
		
		return Constants.INDUSTRY_GROUP;
	}

	public String getGrilsOnlyGroupCategory(String jobType) {
		
		return Constants.GRILS_ONLY_GROUP;
	}

	public String getGroupCategoryBasedOnInterest(String interest) {
		return interest;
	}
}
