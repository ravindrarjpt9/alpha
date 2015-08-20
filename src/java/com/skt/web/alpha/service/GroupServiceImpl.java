package com.skt.web.alpha.service;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.GroupDao;
import com.skt.web.alpha.model.Group;
import com.skt.web.common.exception.ApplicationException;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;

	@Override
	public Group createGroup(Group group) throws ApplicationException {
		// Creating the group in DB
		return groupDao.persist(group);
	}

	@Override
	public Group updateGroup(Group group) throws ApplicationException {
		// Updating the group in DB
		return groupDao.merge(group);
	}

	@Override
	public Group getGroup(int groupId) throws ApplicationException {
		Group group = groupDao.findById(Group.class, groupId);
		if (group == null) {
			throw new ApplicationException("No group exists for the GROUP_ID: "
					+ groupId);
		} else {
			return group;
		}
	}

	@Override
	public Group findByName(String groupName) throws ApplicationException {
		Group group = groupDao.findByName(groupName);
		if (group != null) {
			// Returning the object retrieved from DB
			return group;
		} else {
			// Throwing ApplicationException if object not found in DB
			throw new ApplicationException("No group present for the NAME: "
					+ groupName);
		}
	}
//	@Override
//	public Group updateUserCount(int groupId) throws ApplicationException {
//		
//		return groupDao.updateUserCount(groupId);
//	}
}
