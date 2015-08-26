package com.skt.web.alpha.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.PushRegistration;
import com.skt.web.common.dao.BaseDaoImpl;
import com.skt.web.common.exception.ApplicationException;

@Repository
public class PushRegistrationDaoImpl extends BaseDaoImpl<PushRegistration>
		implements PushRegistrationDao {
	private static final String FIND_ALL_REGISTERED_DEVICE = "findAllRegisteredDevice";

	private static final String FIND_BY_KEY = "findRegistrationKey";

	private static final String FIND_ALL_REGISTERED_DEVICE_BY = "findAllRegisteredDeviceBy";

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllRegisteredDevice() throws ApplicationException {
		Set<String> list = new HashSet<>();
		List<PushRegistration> pushRegs = null;
		try {
			pushRegs = (List<PushRegistration>) getHibernateTemplate()
					.findByNamedQuery(FIND_ALL_REGISTERED_DEVICE);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getLocalizedMessage());
		}
		if (pushRegs != null && !pushRegs.isEmpty()) {
			for (PushRegistration pushRegistration : pushRegs) {
				list.add(pushRegistration.getRegistrationKey());
			}
		}
		return new ArrayList<>(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findBy(String regKey, int userId)
			throws ApplicationException {
		List<PushRegistration> pushRegs = null;
		try {
			pushRegs = (List<PushRegistration>) getHibernateTemplate()
					.findByNamedQuery(FIND_BY_KEY, regKey, userId);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getLocalizedMessage());
		}
		return (pushRegs != null && !pushRegs.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllKeysByUserId(int userId)
			throws ApplicationException {
		Set<String> list = new HashSet<>();
		List<PushRegistration> pushRegs = null;
		try {
			pushRegs = (List<PushRegistration>) getHibernateTemplate()
					.findByNamedQueryAndNamedParam(
							FIND_ALL_REGISTERED_DEVICE_BY, "userIds",
							Arrays.asList(userId));
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getLocalizedMessage());
		}
		if (pushRegs != null && !pushRegs.isEmpty()) {
			for (PushRegistration pushRegistration : pushRegs) {
				list.add(pushRegistration.getRegistrationKey());
			}
		}
		return new ArrayList<>(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllKeysByUserIds(List<Integer> userIds)
			throws ApplicationException {
		Set<String> list = new HashSet<>();
		List<PushRegistration> pushRegs = null;
		try {
			pushRegs = (List<PushRegistration>) getHibernateTemplate()
					.findByNamedQueryAndNamedParam(
							FIND_ALL_REGISTERED_DEVICE_BY, "userIds", userIds);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getLocalizedMessage());
		}
		if (pushRegs != null && !pushRegs.isEmpty()) {
			for (PushRegistration pushRegistration : pushRegs) {
				list.add(pushRegistration.getRegistrationKey());
			}
		}
		return new ArrayList<>(list);
	}
	
	
}