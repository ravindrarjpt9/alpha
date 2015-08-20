package com.skt.web.alpha.util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skt.web.alpha.constants.DeviceType;
import com.skt.web.alpha.model.Registration;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.to.FbEducationHistory;
import com.skt.web.alpha.to.FbGroup;
import com.skt.web.alpha.to.FbLike;
import com.skt.web.alpha.to.FbWorkHistory;
import com.skt.web.alpha.to.RegistrationTo;
import com.skt.web.alpha.to.UserTo;

@Component
public class RegistrationUtils {

	@Autowired
	Gson gson;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	UserUtils userUtils;

	public void setRegistrationFromRegistrationTo(Registration registration,
			RegistrationTo registrationTo) throws ParseException {
		registration.setAboutMe(registrationTo.getAboutMe());
		registration.setCountryCode(registrationTo.getCountryCode());
		registration.setCoverPhotoUrl(registrationTo.getCoverPhotoUrl());
		registration.setDeviceModel(registrationTo.getDeviceModel());
		// Converting int to enum DeviceType
		registration.setDeviceType(DeviceType.values()[registrationTo
				.getDeviceType()]);

		// Converting from String to Date
		String strLocale = registrationTo.getLocale();
		String strDob = registrationTo.getDob();
		// Null check
		if (strDob != null && strLocale != null) {
			Date dob = commonUtils.convertStringToDate(strDob, strLocale);
			registration.setDob(dob);
		}

		registration.setFbEducationHistory(gson.toJson(registrationTo
				.getFbEducationHistory()));
		registration.seteMail(registrationTo.geteMail());
		//registration.setFbLikes(gson.toJson(registrationTo.getFbLikes()));
		registration.setFbGroups(gson.toJson(registrationTo.getFbGroupList()));
		registration.setFbCreationDate(registrationTo.getFbCreatedDate());
		registration.setFbCurrentLocation(registrationTo.getFbCurrentLocation());
		registration.setFbRelationsShips(registrationTo.getFbRelationshipStatus());
		
		registration.setFbTimelineUrl(registrationTo.getFbTimelineUrl());
		registration.setFbUserId(registrationTo.getFbUserId());
		registration.setFbWorkHistory(gson.toJson(registrationTo
				.getFbWorkHistory()));
		registration.setFirstName(registrationTo.getFullName());
		registration.setFullName(registrationTo.getFullName());
		registration.setGender(registrationTo.getGender());
		registration.setId(registrationTo.getId());
		registration.setImeiNo(registrationTo.getImeiNo());
		registration.setInvitationCode(registrationTo.getInvitationCode());
		registration.setLastName(registrationTo.getLastName());
		registration.setLocale(strLocale);
		registration.setManufacturer(registrationTo.getManufacturer());
		registration.setMaritalStatus(registrationTo.getMaritalStatus());
		registration.setMobileNo(registrationTo.getMobileNo());
		registration.setNoOfFriends(registrationTo.getNoOfFriends());
		registration.setOsVersion(registrationTo.getOsVersion());
		//registration.setFbPosts(gson.toJson(registrationTo.getFbPosts()));
		registration.setPushId(registrationTo.getPushId());

		// Convert UserTo to User if, userTo != null
		UserTo userTo = registrationTo.getUserTo();
		User user = null;
		// Null check
		if (userTo != null) {
			user = new User();
			userUtils.setUserFromUserTo(user, userTo);
		}
		registration.setUser(user);

		registration.setUserVerificationTime(registrationTo
				.getUserVerificationTime());
		registration.setVerificationCode(registrationTo.getVerificationCode());
		registration.setVerificationCodeTime(registrationTo
				.getVerificationCodeTime());
		registration.setVerificationStatus(registrationTo
				.getVerificationStatus());
	}

	public void setRegistrationToFromRegistration(
			RegistrationTo registrationTo, Registration registration)
			throws ParseException {
		registrationTo.setAboutMe(registration.getAboutMe());
		registrationTo.setCountryCode(registration.getCountryCode());
		registrationTo.setCoverPhotoUrl(registration.getCoverPhotoUrl());
		registrationTo.setDeviceModel(registration.getDeviceModel());
		// Get int value from enum DeviceType
		registrationTo.setDeviceType(registration.getDeviceType().ordinal());

		// Converting Date to String
		String strLocale = registration.getLocale();
		Date dob = registration.getDob();
		// Null check
		if (dob != null && strLocale != null) {
			String strDob = commonUtils.convertDateToString(dob, strLocale);
			registrationTo.setDob(strDob);
		}

		registrationTo.seteMail(registration.geteMail());
		registrationTo.setFbEducationHistory(gson.fromJson(
				registration.getFbEducationHistory(),
				new TypeToken<List<FbEducationHistory>>() {
				}.getType()));
//		registrationTo.setFbLikes(gson.fromJson(registration.getFbLikes(),
//				new TypeToken<List<FbLike>>() {
//				}.getType()));
		
		registrationTo.setFbGroupList(gson.fromJson(registration.getFbGroups(), new  TypeToken<List<FbGroup>>() {
		}.getType()));
		
		registrationTo.setFbCreatedDate(registration.getFbCreationDate());
		registrationTo.setFbCurrentLocation(registration.getFbCurrentLocation());
		registrationTo.setFbRFbRelationshipStatus(registration.getFbRelationsShips());
		
		
		
		registrationTo.setFbTimelineUrl(registration.getFbTimelineUrl());
		registrationTo.setFbUserId(registration.getFbUserId());
		registrationTo.setFbWorkHistory(gson.fromJson(
				registration.getFbWorkHistory(),
				new TypeToken<List<FbWorkHistory>>() {
				}.getType()));
		registrationTo.setFirstName(registration.getFirstName());
		registrationTo.setFullName(registration.getFullName());
		registrationTo.setGender(registration.getGender());
		registrationTo.setId(registration.getId());
		registrationTo.setImeiNo(registration.getImeiNo());
		registrationTo.setInvitationCode(registration.getInvitationCode());
		registrationTo.setLastName(registration.getLastName());
		registrationTo.setLocale(strLocale);
		registrationTo.setManufacturer(registration.getManufacturer());
		registrationTo.setMaritalStatus(registration.getMaritalStatus());
		registrationTo.setMobileNo(registration.getMobileNo());
		registrationTo.setNoOfFriends(registration.getNoOfFriends());
		registrationTo.setOsVersion(registration.getOsVersion());
//		registrationTo.setFbPosts(gson.fromJson(registration.getFbPosts(),
//				new TypeToken<List<FbLike>>() {
//				}.getType()));
		registrationTo.setPushId(registration.getPushId());

		// Convert user to userTo if user != null
		User user = registration.getUser();
		UserTo userTo = null;
		// Null check
		if (user != null) {
			userTo = new UserTo();
			userUtils.setUserToFromUser(userTo, user);
		}
		registrationTo.setUserTo(userTo);

		registrationTo.setUserVerificationTime(registration
				.getUserVerificationTime());
		registrationTo.setVerificationCode(registration.getVerificationCode());
		registrationTo.setVerificationCodeTime(registration
				.getVerificationCodeTime());
		registrationTo.setVerificationStatus(registration
				.getVerificationStatus());
	}
}
