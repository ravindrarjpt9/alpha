package com.skt.web.alpha.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.GroupUser;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class GroupUserDaoImpl extends BaseDaoImpl<GroupUser> implements
		GroupUserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Object[]> findAllGroupUserTosByUserId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findAllGroupUserTosByUserId")
				.setParameter("userId", userId);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findAllUserNameTosByGroupId(int groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findAllUserNameTosByGroupId")
				.setParameter("groupId", groupId);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findAllUserIdTosByGroupId(int groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findAllUserIdTosByGroupId")
				.setParameter("groupId", groupId);
		return (List<Object[]>) query.list();
	}
	
	@Override
	public List<Integer> findAllGroupIdsByUserId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findAllGroupIdsByUserId")
				.setParameter("userId", userId);
		return (List<Integer>) query.list();
	}

	@Override
	public List<Object[]> findAllGroupDisplayNameTosByUserId(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(
				"findAllGroupDisplayNameTosByUserId").setParameter("userId",
				userId);
		return (List<Object[]>) query.list();
	}

	@Override
	public GroupUser findByUserIdAndGroupId(int userId, int groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findByUserIdAndGroupId")
				.setParameter("userId", userId)
				.setParameter("groupId", groupId);
		return (GroupUser) query.uniqueResult();
	}
}
