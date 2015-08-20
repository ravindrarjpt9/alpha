package com.skt.web.alpha.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skt.web.alpha.model.Photo;
import com.skt.web.alpha.model.User;
import com.skt.web.common.dao.BaseDaoImpl;
@Repository
public class PhotoDaoImpl extends BaseDaoImpl<Photo> implements PhotoDao {

//	@Autowired
//	private SessionFactory sessionFactory;

}
