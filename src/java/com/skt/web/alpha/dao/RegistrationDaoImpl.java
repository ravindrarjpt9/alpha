package com.skt.web.alpha.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skt.web.alpha.model.Registration;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class RegistrationDaoImpl extends BaseDaoImpl<Registration> implements
		RegistrationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Registration findRegistrationWithUsersByFbUserId(String fbUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(
				"findRegistrationWithUserByFbUserId").setParameter("fbUserId",
				fbUserId);
		return (Registration) query.uniqueResult();
	}

	// @Override
	// public String addRegistration(Registration registration) {
	// Session session = this.sessionFactory.getCurrentSession();
	// session.persist(registration);
	// return "Inside RegistrationDaoImpl.testDao(). str is: " + registration
	// + "\n sessionFactory is: " + sessionFactory;
	// }
}
