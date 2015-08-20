package com.skt.web.alpha.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skt.web.alpha.model.User;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByFbUserId(String fbUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findUserByFbUserId").setParameter(
				"fbUserId", fbUserId);
		return (User) query.uniqueResult();
	}

}
