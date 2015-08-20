package com.skt.web.alpha.dao;

import com.skt.web.alpha.model.Registration;
import com.skt.web.common.dao.BaseDao;

public interface RegistrationDao extends BaseDao<Registration> {

	Registration findRegistrationWithUsersByFbUserId(String fbUserId);
}
