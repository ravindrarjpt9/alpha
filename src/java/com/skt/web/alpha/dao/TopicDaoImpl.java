package com.skt.web.alpha.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.Topic;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Object[]> findAllTopicTosByGroupIds(List<Integer> groupIds) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findAllTopicTosByGroupIds")
				.setParameterList("groupIds", groupIds);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findAllTopicTosByOwnerUserId(int ownerUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findAllTopicTosByOwnerUserId")
				.setParameter("ownerUserId", ownerUserId);
		return (List<Object[]>) query.list();
	}
	
	@Override
	public List<String> findAllTopicListByGroupCategory(String groupCategory) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select TOPIC from `topic_list` where group_name = :groupCategory")
				.setParameter("groupCategory", groupCategory.trim()).setCacheable(true);
		return (List<String>) query.list();
	}
	
	@Override
	public List<Integer> findAllTopicListByGroupId(int groupId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select ID from topics where GROUP_ID = :groupId")
				.setInteger("groupId", groupId).setCacheable(true);
		return (List<Integer>) query.list();
	}

}
