package com.skt.web.alpha.service;

import com.skt.web.alpha.model.User;
import com.skt.web.common.exception.ApplicationException;

public interface UserService {

	User createUser(User user) throws ApplicationException;

	User updateUser(User user) throws ApplicationException;

	User getUser(int userId) throws ApplicationException;

	User getUserByFbUserId(String fbUserId) throws ApplicationException;
}
