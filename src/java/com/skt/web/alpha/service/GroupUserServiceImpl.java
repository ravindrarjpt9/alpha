package com.skt.web.alpha.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skt.web.alpha.dao.GroupUserDao;
import com.skt.web.alpha.model.GroupUser;
import com.skt.web.common.exception.ApplicationException;

@Service
public class GroupUserServiceImpl implements GroupUserService {

	@Autowired
	GroupUserDao groupUserDao;

	@Override
	public GroupUser createGroupUser(GroupUser groupUser)
			throws ApplicationException {
		// Creating the groupUser in DB
		return groupUserDao.persist(groupUser);
	}

	@Override
	public GroupUser updateGroupUser(GroupUser groupUser)
			throws ApplicationException {
		// Updating the groupUser in DB
		return groupUserDao.merge(groupUser);
	}

	@Override
	public GroupUser getGroupUser(int groupUserId) throws ApplicationException {
		GroupUser groupUser = groupUserDao.findById(GroupUser.class,
				groupUserId);
		if (groupUser == null) {
			throw new ApplicationException(
					"No groupUser exists for the GROUP_USER_ID: " + groupUserId);
		} else {
			return groupUser;
		}
	}

	@Override
	public List<Object[]> findAllGroupUserTosByUserId(int userId)
			throws ApplicationException {

		List<Object[]> groupUserTos = groupUserDao
				.findAllGroupUserTosByUserId(userId);

		if (groupUserTos.isEmpty()) {
			throw new ApplicationException(
					"No groupUser exists for the USER_ID: " + userId);
		} else {
			return groupUserTos;
		}
	}

	@Override
	public List<Object[]> findAllUserNameTosByGroupId(int groupId)
			throws ApplicationException {

		List<Object[]> userNameTos = groupUserDao
				.findAllUserNameTosByGroupId(groupId);

		if (userNameTos.isEmpty()) {
			throw new ApplicationException("No users exists for the GROUP_ID: "
					+ groupId);
		} else {
			return userNameTos;
		}
	}

	@Override
	public List<Object[]> findAllUserIdTosByGroupId(int groupId)
			throws ApplicationException {
		List<Object[]> userNameTos = groupUserDao
				.findAllUserIdTosByGroupId(groupId);

		if (userNameTos.isEmpty()) {
			throw new ApplicationException("No users exists for the GROUP_ID: "
					+ groupId);
		} else {
			return userNameTos;
		}
	}
	@Override
	public List<Integer> findAllGroupIdsByUserId(int userId)
			throws ApplicationException {

		List<Integer> groupIds = groupUserDao.findAllGroupIdsByUserId(userId);

		if (groupIds.isEmpty()) {
			throw new ApplicationException("No groups exists for the USER_ID: "
					+ userId);
		} else {
			return groupIds;
		}
	}

	@Override
	public List<Object[]> findAllGroupDisplayNameTosByUserId(int userId)
			throws ApplicationException {

		List<Object[]> groupDisplayNameTos = groupUserDao
				.findAllGroupDisplayNameTosByUserId(userId);

		if (groupDisplayNameTos.isEmpty()) {
			throw new ApplicationException("No groups exists for the USER_ID: "
					+ userId);
		} else {
			return groupDisplayNameTos;
		}
	}

	@Override
	public GroupUser findByUserIdAndGroupId(int userId, int groupId)
			throws ApplicationException {

		GroupUser groupUser = groupUserDao.findByUserIdAndGroupId(userId,
				groupId);
		if (groupUser == null) {
			throw new ApplicationException(
					"No groupUser exists for the USER_ID: " + userId
							+ " and GROUP_ID: " + groupId);
		} else {
			return groupUser;
		}
	}
}
