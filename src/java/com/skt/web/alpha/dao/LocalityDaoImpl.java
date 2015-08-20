package com.skt.web.alpha.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.Locality;
import com.skt.web.common.dao.BaseDaoImpl;

@Repository
public class LocalityDaoImpl extends BaseDaoImpl<Locality> implements LocalityDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Locality getLocalityByLocation(String localityName,String city) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Locality where lower(localityName) = :localityName and lower(city) = :city");
		query.setString("localityName", localityName.toLowerCase());
		query.setString("city", city.toLowerCase());
		query.setMaxResults(1);
		List<Locality> list = query.list();
		if(list.size() > 0)
		{
		return (Locality) list.get(0);
		}
		else
		{
			return null;
		
		}
	}
	
	@Override
	public List<Locality> getLocalityList() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from Locality").list();
	}
}
