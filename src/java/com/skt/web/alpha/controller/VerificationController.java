package com.skt.web.alpha.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skt.web.alpha.Thread.TopicJoinThreadForNewUsers;
import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.Gender;
import com.skt.web.alpha.constants.GroupGenderCategory;
import com.skt.web.alpha.constants.GroupIconCategory;
import com.skt.web.alpha.constants.UserGroupRole;
import com.skt.web.alpha.constants.UserGroupStatus;
import com.skt.web.alpha.constants.VerificationStatus;
import com.skt.web.alpha.constants.XmppChatRoomRole;
import com.skt.web.alpha.model.Group;
import com.skt.web.alpha.model.GroupUser;
import com.skt.web.alpha.model.Locality;
import com.skt.web.alpha.model.Registration;
import com.skt.web.alpha.model.User;
import com.skt.web.alpha.service.GroupService;
import com.skt.web.alpha.service.GroupUserService;
import com.skt.web.alpha.service.LocalityService;
import com.skt.web.alpha.service.RegistrationService;
import com.skt.web.alpha.service.TopicService;
import com.skt.web.alpha.service.UserService;
import com.skt.web.alpha.service.XmppChatRoomService;
import com.skt.web.alpha.service.XmppUserService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.to.UserTo;
import com.skt.web.alpha.to.VerificationTo;
import com.skt.web.alpha.to.XmppChatRoomTo;
import com.skt.web.alpha.to.XmppUserTo;
import com.skt.web.alpha.util.CommonUtils;
import com.skt.web.alpha.util.UserUtils;
import com.skt.web.alpha.util.VerificationControllerUtils;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class VerificationController {
	private static final Logger LOG = Logger
			.getLogger(VerificationController.class);

	@Autowired
	RegistrationService registrationService;

	@Autowired
	UserService userService;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	XmppUserService xmppUserService;

	@Autowired
	VerificationControllerUtils verificationControllerUtils;

	@Autowired
	TopicService topicService;
	@Autowired
	UserUtils userUtils;

	@Transactional
	@RequestMapping(value = "/verification", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response verify(@RequestBody VerificationTo verificationTo) {
		boolean success = false;
		Object data = null;

		// Setting the current DateTime as userVerificationTime as soon as
		// request is received
		Date userVerificationTime = new Date();

		// Extracting the details from verificationTo
		int registrationId = verificationTo.getRegistrationId();

		String verificationCode = verificationTo.getVerificationCode();

		try {
			// Finding the registration for this REGISTRATION_ID
			// (Throws ApplicationException if registration not present in DB)
			Registration registration = registrationService.get(registrationId);
			User user = null;

			try {
				// Check if a user with this fbUserId already exists in DB
				user = userService
						.getUserByFbUserId(registration.getFbUserId());
			} catch (ApplicationException e) {
				// Do nothing as a new user will be created
			}

			if (user != null) {
				// TODO: In V2, Set the verificationStatus as
				// USER_ALREADY_EXISTS
				// registration.setVerificationStatus(VerificationStatus.USER_ALREADY_EXISTS);
				// registrationService.update(registration);
				// If user already exists then, throw an exception
				throw new ApplicationException(
						Constants.ERROR_MSG_USER_ALREADY_EXITS);
			}

			// Update userVerificatioTime
			registration.setUserVerificationTime(userVerificationTime);

			VerificationStatus verificationStatus = registration
					.getVerificationStatus();

			// Using XOR as NOT_APPLICABLE and Valid verificationCode can not
			// exist at the same time
			if ((verificationStatus.equals(VerificationStatus.NOT_APPLICABLE))
					^ (verificationCode != null && verificationCode
							.equalsIgnoreCase(registration
									.getVerificationCode()))) {

				// Calculating the time difference of verification if
				// verification is applicable
				long timeDiffInMillis = 0;
				if (!verificationStatus
						.equals(VerificationStatus.NOT_APPLICABLE)) {
					timeDiffInMillis = commonUtils.compareDatesInMinutes(
							userVerificationTime,
							registration.getVerificationCodeTime(),
							Constants.VERIFICATION_MINUTES_RANGE);
				}

				if (timeDiffInMillis <= 0) {
					// Creating the user
					user = new User();

					// Populating user from registration
					userUtils.setUserFromRegistration(user, registration);

					// Populating user from verificationTo
					userUtils.setUserFromVerificationTo(user, verificationTo);

					// Persisting user into DB
					// TODO Auto-persist user by
					// registrationDao.persist(registration);
					// if this is a new registration
					user = userService.createUser(user);

					// Assigning the userId to registration
					registration.setUser(user);

					// TODO: No Verification in V1 :'(
					// Updating the verification status
					// registration
					// .setVerificationStatus(VerificationStatus.VERIFIED);

					// Updating registration in DB
					registration = registrationService.update(registration);

					// Once the user has been successfully created and
					// registered, create the user in XMPP server also
					verificationControllerUtils.createXmppUser(user,
							xmppUserService);

					// Creating group(s) for user based on GroupJoiningLogic
					verificationControllerUtils.groupsJoiningLogicForUser(user);

					// Update the user in DB after group creating (CollegeName,
					// etc.)
					// TODO: Known issue: To be resolved
					// TODO: user = userService.updateUser(user);

					// Creating new instance of userTo
					UserTo userTo = new UserTo();
					// Setting values of userTo from user
					userUtils.setUserToFromUser(userTo, user);

					// Sending UserTo after successful verification and
					// groupCreation
					data = userTo;
					success = true;
				} else {
					registration
							.setVerificationStatus(VerificationStatus.EXPIRED);
					registration.setUserVerificationTime(userVerificationTime);
					throw new ApplicationException(
							Constants.ERROR_MSG_VERIFICATION_CODE_EXPIRED
									+ registrationId);
				}
			} else {
				registration
						.setVerificationStatus(VerificationStatus.INVALID_CODE);
				throw new ApplicationException(
						Constants.ERROR_MSG_INVALID_VERIFICATION_CODE
								+ registrationId);
			}
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	// TODO: Move the below functions to a new class
}
