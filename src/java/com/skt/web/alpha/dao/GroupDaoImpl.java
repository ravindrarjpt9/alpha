package com.skt.web.alpha.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.Group;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Group findByName(String groupName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findByName").setParameter(
				"groupName", groupName);
		return (Group) query.uniqueResult();
	}
	
	
}
