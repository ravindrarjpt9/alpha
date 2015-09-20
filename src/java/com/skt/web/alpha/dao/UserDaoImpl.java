package com.skt.web.alpha.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.User;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	private static int noOfRecords;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByFbUserId(String fbUserId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("findUserByFbUserId").setParameter(
				"fbUserId", fbUserId);
		return (User) query.uniqueResult();
	}

	@Override
	public List<User> getUsers(int pageSize, int page, String sidx, String sord) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from User order by " + sidx + " " + sord);
		 noOfRecords = query.list().size();
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		return (List<User>) query.list();
	}

	public  int getNoOfRecords() {
		return noOfRecords;
	}

	@Override
	public void getDeleteUsers(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query groupUser = session.createSQLQuery("delete  from groups_users where id =:id ");
		Query registrations = session.createSQLQuery("delete  from registrations where id =:id ");
		Query users = session.createSQLQuery("delete from users where id =:id");
		groupUser.setParameter("id", userId);
		registrations.setParameter("id", userId);
		users.setParameter("id", userId);
		groupUser.executeUpdate();
		session.flush();
		registrations.executeUpdate();
		session.flush() ;
		users.executeUpdate();
	    session.flush() ;
	    
		//return null;
	}
	
	
	

}
