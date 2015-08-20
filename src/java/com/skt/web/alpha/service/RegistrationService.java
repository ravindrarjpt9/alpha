package com.skt.web.alpha.service;

import com.skt.web.alpha.model.Registration;
import com.skt.web.common.exception.ApplicationException;

public interface RegistrationService {

	Registration register(Registration registration)
			throws ApplicationException;

	Registration get(int registrationId) throws ApplicationException;

	Registration update(Registration registration) throws ApplicationException;

	Registration findRegistrationWithUsersByFbUserId(String fbUserId)
			throws ApplicationException;
}
