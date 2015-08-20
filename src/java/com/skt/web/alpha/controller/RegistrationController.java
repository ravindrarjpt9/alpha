package com.skt.web.alpha.controller;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skt.web.alpha.constants.Constants;
import com.skt.web.alpha.constants.VerificationStatus;
import com.skt.web.alpha.model.Registration;
import com.skt.web.alpha.service.PushRegistrationService;
import com.skt.web.alpha.service.RegistrationService;
import com.skt.web.alpha.to.ErrorResponse;
import com.skt.web.alpha.to.RegistrationTo;
import com.skt.web.alpha.to.Response;
import com.skt.web.alpha.util.RegistrationUtils;
import com.skt.web.common.exception.ApplicationException;

@Controller
public class RegistrationController {
	private static final Logger LOG = Logger
			.getLogger(RegistrationController.class);

	@Autowired
	RegistrationService registrationService;

	@Autowired
	PushRegistrationService pushRegistrationService;

	@Autowired
	RegistrationUtils registrationUtils;

	@Transactional
	@RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Response registrer(@RequestBody RegistrationTo registrationTo) {
		boolean success = false;
		Object data = null;

		// This will be useful if more than one condition can leads to
		// INVALID_USER
		String invalidUserMsg = null;

		Registration registration = null;

		try {
			try {
				// Checking if a registration with user already exists for this
				// fbUserId
				registration = registrationService
						.findRegistrationWithUsersByFbUserId(registrationTo
								.getFbUserId());

				// Populating registrationTo for sending to client
				try {
					registrationUtils.setRegistrationToFromRegistration(
							registrationTo, registration);
				} catch (ParseException e1) {
					throw new ApplicationException(
							Constants.ERROR_MSG_UNABLE_TO_CONVERT_DATE_TO_STRING);
				}

				data = registrationTo;
				success = true;
			} catch (ApplicationException e) {
				// Create new registration if registration does not exist
				registration = new Registration();
				// Populating registration from registrationTo
				try {
					registrationUtils.setRegistrationFromRegistrationTo(
							registration, registrationTo);
				} catch (ParseException e1) {
					throw new ApplicationException(
							Constants.ERROR_MSG_DOB_PARSE_EXCEPTION);
				}

				// Checking if Minimum number of friends criteria is satisfied
				// or not
				if (registration.getNoOfFriends() < Constants.MINIMUM_FRIENDS_REQUIRED) {
					registration
							.setVerificationStatus(VerificationStatus.INVALID_USER);
					invalidUserMsg = Constants.ERROR_MSG_INVALID_USER;
				} else {
					// TODO: No verification in V1 :'(
					// Generating the verification code:
					// registration.setVerificationCode(CommonUtils
					// .generateRandomString(Constants.VERIFICATION_CODE_LENGTH));
					// registration.setVerificationCodeTime(new Date());
					// registration.setVerificationStatus(VerificationStatus.PENDING);

					registration
							.setVerificationStatus(VerificationStatus.NOT_APPLICABLE);
				}

				// Persisting in DB
				registration = registrationService.register(registration);

				// Throwing exception if INVALID_USER
				if (registration.getVerificationStatus().equals(
						VerificationStatus.INVALID_USER)) {
					throw new ApplicationException(invalidUserMsg);
				}

				// Populating registrationTo for sending to client
				try {
					registrationUtils.setRegistrationToFromRegistration(
							registrationTo, registration);
				} catch (ParseException e1) {
					throw new ApplicationException(
							Constants.ERROR_MSG_UNABLE_TO_CONVERT_DATE_TO_STRING);
				}
				data = registrationTo;

				// TODO: Put code to send SMS instead of Push Notification
				// Sending the registration code as a push notification to the
				// user
				// pushRegistrationService.send(
				// registration.getPushId(),
				// Constants.VERIFICATION_PUSH_MESSAGE
				// + registration.getVerificationCode());

				success = true;
			}
		} catch (ApplicationException e) {
			data = new ErrorResponse(e.getErrorCode(), e.getMessage());
		}
		return new Response(success, data);
	}

	// TODO: No resendVerification in V1 :'(
	// @Transactional
	// @RequestMapping(value = "/resendVerification", method =
	// RequestMethod.POST, consumes = "application/json", produces =
	// "application/json")
	// @ResponseBody
	// public Response resendVerification(@RequestBody int registrationId) {
	// boolean success = false;
	// Object data = null;
	// try {
	// // Fetching the registration object from DB
	// // (Throws exception if null is returned)
	// Registration registration = registrationService.get(registrationId);
	//
	// // Re-generating the verification code
	// registration.setVerificationCode(CommonUtils
	// .generateRandomString(Constants.VERIFICATION_CODE_LENGTH));
	// registration.setVerificationCodeTime(new Date());
	// registration.setVerificationStatus(VerificationStatus.RESENT);
	//
	// // Updating registration in DB
	// registration = registrationService.update(registration);
	//
	// // Sending the re-generated verification code as a push notification
	// // to the user
	// pushRegistrationService.send(
	// registration.getPushId(),
	// Constants.VERIFICATION_PUSH_MESSAGE
	// + registration.getVerificationCode());
	//
	// // Returning the verification code to the mobile client
	// data = registration.getVerificationCode();
	// success = true;
	// } catch (ApplicationException e) {
	// data = new ErrorResponse(e.getErrorCode(), e.getMessage());
	// }
	// return new Response(success, data);
	// }
}
