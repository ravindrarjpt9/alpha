package com.skt.web.alpha.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.User;
import com.skt.web.alpha.model.UserPushMessage;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class UserPushMessageDaoImpl extends BaseDaoImpl<UserPushMessage> implements UserPushMessageDao {

private static int noOfRecords;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<UserPushMessage> getUserPushMessage(int pageSize, int page,
			String sidx, String sord, int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from UserPushMessage where uId ="+id+" order by " + sidx + " " + sord);
		 noOfRecords = query.list().size();
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		return (List<UserPushMessage>) query.list();
	}
	
	@Override
	public int getNoOfMessages() {
		
		return noOfRecords;
	}
}
