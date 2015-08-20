package com.skt.web.alpha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.RegistrationDao;
import com.skt.web.alpha.model.Registration;
import com.skt.web.common.exception.ApplicationException;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	RegistrationDao registrationDao;

	@Override
	public Registration register(Registration registration)
			throws ApplicationException {
		// Persisting into DB
		registrationDao.persist(registration);

		// Returning the registration
		return registration;
	}

	@Override
	public Registration get(int registrationId) throws ApplicationException {

		// Getting from DB
		Registration registration = registrationDao.findById(
				Registration.class, registrationId);

		if (registration != null) {
			// Returning the object retrieved from DB
			return registration;
		} else {
			// Throwing ApplicationException if object not found in DB
			throw new ApplicationException(
					"No registraion present for the REGISTRATION_ID: "
							+ registrationId);
		}
	}

	@Override
	public Registration update(Registration registration)
			throws ApplicationException {

		// Updating in DB and returning the updated object
		return registrationDao.merge(registration);
	}

	@Override
	public Registration findRegistrationWithUsersByFbUserId(String fbUserId)
			throws ApplicationException {
		Registration registration = registrationDao
				.findRegistrationWithUsersByFbUserId(fbUserId);

		if (registration == null) {
			throw new ApplicationException(
					"No registration exists for the FB_USER_ID: " + fbUserId);
		} else {
			return registration;
		}
	}
}
