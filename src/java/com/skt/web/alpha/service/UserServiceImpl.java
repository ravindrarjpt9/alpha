package com.skt.web.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.UserDao;
import com.skt.web.alpha.model.User;
import com.skt.web.common.exception.ApplicationException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public User createUser(User user) throws ApplicationException {
		// Creating the user in DB
		return userDao.persist(user);
	}

	@Override
	public User updateUser(User user) throws ApplicationException {
		// Updating the user in DB
		return userDao.merge(user);
	}

	@Override
	public User getUser(int userId) throws ApplicationException {
		User user = userDao.findById(User.class, userId);
		if (user == null) {
			throw new ApplicationException("No user exists for the USER_ID: "
					+ userId);
		} else {
			return user;
		}
	}

	@Override
	public User getUserByFbUserId(String fbUserId) throws ApplicationException {
		User user = userDao.findByFbUserId(fbUserId);

		if (user != null) {
			// Returning the object retrieved from DB
			return user;
		} else {
			// Throwing ApplicationException if object not found in DB
			throw new ApplicationException(
					"No user present for the FB_USER_ID: " + fbUserId);
		}
	}
	
	@Override
	public List<User> getUsers(int pageSize, int page, String sidx, String sord)  throws ApplicationException{
		
		return userDao.getUsers(pageSize,page,sidx,sord);
	}
	
	@Override
	public int getNoOfUsers() throws ApplicationException {
		return userDao.getNoOfRecords();
	}
	
	@Override
	public void deleteUser(int userId) throws ApplicationException {
		userDao.getDeleteUsers(userId);
		
	}
	
	
}
